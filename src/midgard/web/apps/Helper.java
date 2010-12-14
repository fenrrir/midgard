/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web.apps;

import java.io.ByteArrayInputStream;
import midgard.web.IHTTPServer;
import midgard.web.Response;
import midgard.web.json.JSONObject;

/**
 *
 * @author fenrrir
 */
public class Helper {

    public static Response getResponse(String body){
        return new Response(IHTTPServer.HTTP_OK, IHTTPServer.MIME_HTML,
                            new ByteArrayInputStream(body.getBytes()), body.length());
    }

    public static Response getResponse(JSONObject json){
        String body = json.toString();
        return new Response(IHTTPServer.HTTP_OK, IHTTPServer.MIME_APPLICATION_JSON,
                            new ByteArrayInputStream(body.getBytes()),
                            body.length());
    }

}
