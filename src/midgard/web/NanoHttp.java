/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midgard.web;

import com.sun.squawk.util.StringTokenizer;
import java.io.*;
import java.util.*;
import javax.microedition.io.Datagram;
import midgard.componentmodel.Component;
import midgard.web.events.RequestEvent;
import midgard.web.events.ResponseEvent;

/**
 *
 * @author fenrrir
 */
/**
 * A simple, tiny, nicely embeddable HTTP 1.0 server in Java
 *
 * <p> NanoHTTPD version 1.01,
 * Copyright &copy; 2001 Jarno Elonen (elonen@iki.fi, http://iki.fi/elonen/)
 *
 * <p><b>Features & limitations: </b><ul>
 *
 *    <li> Java 1.1 compatible </li>
 *    <li> Released as open source, Modified BSD licence </li>
 *    <li> No fixed config files, logging, authorization etc. (Implement yourself if you need them.) </li>
 *    <li> Supports parameter parsing of GET and POST methods </li>
 *    <li> Never caches anything </li>
 *    <li> Doesn't limit bandwidth, request time or simultaneous connections </li>
 *
 * </ul>
 *
 * See the end of the source file for distribution license
 * (Modified BSD licence)
 */
public class NanoHttp extends Component implements IHTTPServer{

    /**
     * Some HTTP response status codes
     */
    public static final String HTTP_OK = "200 OK",
            HTTP_REDIRECT = "301 Moved Permanently",
            HTTP_FORBIDDEN = "403 Forbidden",
            HTTP_NOTFOUND = "404 Not Found",
            HTTP_BADREQUEST = "400 Bad Request",
            HTTP_INTERNALERROR = "500 Internal Server Error",
            HTTP_NOTIMPLEMENTED = "501 Not Implemented";
    /**
     * Common mime types for dynamic content
     */
    public static final String MIME_PLAINTEXT = "text/plain",
            MIME_HTML = "text/html",
            MIME_APPLICATION_JSON = "application/json",
            MIME_DEFAULT_BINARY = "application/octet-stream";
    /**
     * Vector to store all registered Web Applications
     */
    private Hashtable handlers = new Hashtable();

    /**
     * Register a new Web Application
     */
    public void addView(URLView view) {
        if (!handlers.containsKey(view.getUri())){
            handlers.put(view.getUri(), view);
        }
    }


    // ==================================================
    // Socket & server code
    // ==================================================
    private String readLine(Reader in) throws IOException {
        StringBuffer res = new StringBuffer();
        int ch;
        while ((ch = in.read()) > 0) {
            if (ch == '\n') {
                break;
            }
            if (ch != '\r') {
                res.append((char) ch);
            }
        }
        return res.toString();
    }

    public void handleRequest(Datagram input, Datagram output) throws IOException {
        try {

            ByteArrayInputStream byteArrayInput =
                    new ByteArrayInputStream(input.getData());
            Reader in = new InputStreamReader(byteArrayInput);




            // Read the request line
            StringTokenizer st = new StringTokenizer(readLine(in));
            if (!st.hasMoreTokens()) {
                sendError(output, HTTP_BADREQUEST, "BAD REQUEST: Syntax error");
                return;
            }

            String method = st.nextToken();

            if (!st.hasMoreTokens()) {
                sendError(output, HTTP_BADREQUEST, "BAD REQUEST: Missing URI");
                return;
            }

            String uri = decodePercent(st.nextToken());

            String parms = null;

            // Separate parameters from the URI
            int qmi = uri.indexOf('?');
            if (qmi >= 0) {

                parms = uri.substring(qmi + 1);

                uri = decodePercent(uri.substring(0, qmi));
            }


            Request request = new Request(method, uri);

            // Decode parameters
            if (parms != null) {
                decodeParms(parms, request.parms);
            }

            // If there's another token, it's protocol version,
            // followed by HTTP headers. Ignore version but parse headers.
            if (st.hasMoreTokens()) {
                String line = readLine(in);

                while (line.trim().length() > 0) {
                    int p = line.indexOf(':');
                    request.header.put(line.substring(0, p).trim(), line.substring(p + 1).trim());
                    line = readLine(in);
                }
            }

            // If the method is POST, there may be parameters
            // in data section, too, read another line:
            if (method.equals("POST")) {
                decodeParms(readLine(in), request.parms);
            }



            fireEvent(new RequestEvent(request));
            IURLHandler handler = (IURLHandler) handlers.get(request.uri);
            
            if (handler != null) {
                Response response = handler.serve(request);
                fireEvent( new ResponseEvent(response));
                sendResponse(output, response);
            } else {
                sendError(output, HTTP_NOTFOUND, HTTP_NOTFOUND);
            }

        } catch (IllegalArgumentException iae) {
            sendError(output, HTTP_BADREQUEST, iae.toString());
        } catch (Exception e) {
            e.printStackTrace();
            sendError(output, HTTP_INTERNALERROR, "SERVER INTERNAL ERROR: Exception: " + e.toString());
        }
    }

    /**
     * Decodes the percent encoding scheme. <br/>
     * For example: "an+example%20string" -> "an example string"
     */
    private String decodePercent(String str) throws IllegalArgumentException {
        try {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                switch (c) {
                    case '+':
                        sb.append(' ');
                        break;
                    case '%':
                        sb.append((char) Integer.parseInt(str.substring(i + 1, i + 3), 16));
                        i += 2;
                        break;
                    default:
                        sb.append(c);
                        break;
                }
            }
            return sb.toString();
        } catch (Exception e) {
            throw new IllegalArgumentException("BAD REQUEST: Bad percent-encoding.");
        }
    }

    /**
     * Decodes parameters in percent-encoded URI-format
     * ( e.g. "name=Jack%20Daniels&pass=Single%20Malt" ) and
     * adds them to given Properties.
     */
    private void decodeParms(String parms, Properties p) {
        if (parms == null) {
            return;
        }

        StringTokenizer st = new StringTokenizer(parms, "&");
        while (st.hasMoreTokens()) {
            String e = st.nextToken();
            int sep = e.indexOf('=');
            if (sep >= 0) {
                p.put(decodePercent(e.substring(0, sep)).trim(),
                        decodePercent(e.substring(sep + 1)));
            }
        }
    }

    /**
     * Returns an error message as a HTTP response.
     */
    private void sendError(Datagram output, String status, String msg) throws IOException {
        sendResponse(output, new Response(status, MIME_PLAINTEXT, msg));
    }

    /**
     * Sends given response to the socket.
     */
    private void sendResponse(Datagram output, Response response) throws IOException {
        String status = response.status;
        String mime = response.mimeType;
        Properties header = response.header;
        InputStream data = response.data;
        int contentLength = response.contentLength;

        ByteArrayOutputStream byteArrayOutput =
                new ByteArrayOutputStream(output.getLength());


        if (status == null) {
            throw new Error("sendResponse(): Status can't be null.");
        }

        Writer out = new OutputStreamWriter(byteArrayOutput);
        out.write("HTTP/1.0 " + status + " \r\n");

        if (contentLength >= 0) {
            out.write("Content-Length: " + contentLength + "\r\n");
        }

        if (mime != null) {
            out.write("Content-Type: " + mime + "\r\n");
        }


        Enumeration e = header.keys();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = header.getProperty(key);
            out.write(key + ": " + value + "\r\n");
        }

        out.write("\r\n");
        out.flush();

        if (data != null) {
            byte[] buff = new byte[2048];
            int read = 2048;
            while (read == 2048) {
                read = data.read(buff, 0, 2048);
                output.write(buff, 0, read);
            }
        }
        if (data != null) {
            data.close();
        }
    }
}
