package midgard.web;

import com.sun.squawk.util.StringTokenizer;
import java.io.*;
import java.util.*;
import midgard.componentmodel.Component;
import midgard.kernel.Debug;
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

    public void removeViewWithURI(String URI) {
        if (handlers.contains(URI)){
            handlers.remove(URI);
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

    public String handleRequest(String request){
        Request requestObj;
        Response response;
        try {

            
            ByteArrayInputStream byteArray = new ByteArrayInputStream(request.getBytes());
            Reader in = new InputStreamReader(byteArray);

            // Read the request line
            String line = readLine(in);
            StringTokenizer st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) {
                return sendError(HTTP_BADREQUEST, "BAD REQUEST: Syntax error");
            }

            String method = st.nextToken();

       

            if (!st.hasMoreTokens()) {
                return sendError(HTTP_BADREQUEST, "BAD REQUEST: Missing URI");

            }

            String uri = decodePercent(st.nextToken());

            String parms = null;

            // Separate parameters from the URI
            int qmi = uri.indexOf('?');
            if (qmi >= 0) {

                parms = uri.substring(qmi + 1);

                uri = decodePercent(uri.substring(0, qmi));
            }


            requestObj = new Request(method, uri);

            // Decode parameters
            if (parms != null) {
                decodeParms(parms, requestObj.parms);
            }

            // If there's another token, it's protocol version,
            // followed by HTTP headers. Ignore version but parse headers.
            if (st.hasMoreTokens()) {
                line = readLine(in);

                while (line.trim().length() > 0) {
                    int p = line.indexOf(':');
                    requestObj.header.put(line.substring(0, p).trim(), line.substring(p + 1).trim());
                    line = readLine(in);
                }
            }

            // If the method is POST, there may be parameters
            // in data section, too, read another line:
            if (method.equals("POST")) {
                decodeParms(readLine(in), requestObj.parms);
            }



            fireEvent(new RequestEvent(requestObj));
            IURLHandler handler = (IURLHandler) handlers.get(requestObj.uri);

            
            if (handler != null) {
                Debug.debug("[APP] serve call", 1);
                response = handler.serve(requestObj);
                Debug.debug("[APP] serve response", 1);
                response.uri = requestObj.uri;
                fireEvent( new ResponseEvent(response));
                return sendResponse(response);
            } else {
                return sendError(HTTP_NOTFOUND, HTTP_NOTFOUND);
            }

        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            return sendError(HTTP_BADREQUEST, iae.toString());
        }catch(NotRequestException nre){
            throw nre;
        } catch (Exception e) {
            e.printStackTrace();
            return sendError(HTTP_INTERNALERROR, "SERVER INTERNAL ERROR: Exception: " + e.toString());
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
    private String sendError(String status, String msg){
        return sendResponse(new Response(status, MIME_PLAINTEXT, msg));
    }

    /**
     * Sends given response to the socket.
     */
    private String sendResponse(Response response){

        StringBuffer strBuffer =  new StringBuffer();
        String status = response.status;
        String mime = response.mimeType;
        Properties header = response.header;
        int contentLength = response.contentLength;



        if (status == null) {
            throw new Error("sendResponse(): Status can't be null.");
        }



        strBuffer.append("HTTP/1.0 " + status + " \r\n");

        if (contentLength >= 0) {
            strBuffer.append("Content-Length: " + contentLength + "\r\n");
        }

        if (mime != null) {
            strBuffer.append("Content-Type: " + mime + "\r\n");
        }


        Enumeration e = header.keys();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = header.getProperty(key);
            strBuffer.append(key + ": " + value + "\r\n");
        }

        strBuffer.append("\r\n");
        strBuffer.append(response.data);
        return strBuffer.toString();

    }
}
