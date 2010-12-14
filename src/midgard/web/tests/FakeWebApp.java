/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web.tests;

import java.io.ByteArrayInputStream;
import java.util.Vector;
import midgard.componentmodel.Component;
import midgard.web.IHTTPServer;
import midgard.web.IWebApplication;
import midgard.web.Request;
import midgard.web.Response;

/**
 *
 * @author fenrrir
 */
public class FakeWebApp extends Component implements IWebApplication {
    private Vector uris;

    

    public void destroy() {
        super.destroy();
        uris.removeAllElements();
        uris = null;
    }

    public void initialize() {
        super.initialize();
        uris = new Vector();
        uris.addElement("/");
        uris.addElement("/foobar");
    }



    
    private Response getHome(){
        return getResponse("Hello World");
        
    }

    private Response getFoo(){
        return getResponse("FooBar");
    }

    private Response getResponse(String body){
        return new Response(IHTTPServer.HTTP_OK, IHTTPServer.MIME_HTML,
                            new ByteArrayInputStream(body.getBytes()), body.length());
    }

    public Vector getURIs() {
        return uris;
    }

    public Response serve(Request request) throws Exception {
        if (request.uri.equals("/foobar")){
            return getFoo();
        }
        return getHome();
    }



}
