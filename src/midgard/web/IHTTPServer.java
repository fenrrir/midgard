/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Datagram;
import midgard.componentmodel.IComponent;

/**
 *
 * @author fenrrir
 */
public interface IHTTPServer extends IComponent {

    public static final String HTTP_OK = "200 OK",
            HTTP_REDIRECT = "301 Moved Permanently",
            HTTP_FORBIDDEN = "403 Forbidden",
            HTTP_NOTFOUND = "404 Not Found",
            HTTP_BADREQUEST = "400 Bad Request",
            HTTP_INTERNALERROR = "500 Internal Server Error",
            HTTP_NOTIMPLEMENTED = "501 Not Implemented";

    public static final String MIME_PLAINTEXT = "text/plain",
            MIME_HTML = "text/html",
            MIME_APPLICATION_JSON = "application/json",
            MIME_DEFAULT_BINARY = "application/octet-stream";


    public void handleRequest(Datagram input, Datagram output) throws IOException;
    public void addView(URLView view);
    public void removeViewWithURI(String URI);
}
