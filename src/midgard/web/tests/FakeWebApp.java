/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web.tests;

import java.util.Vector;
import midgard.web.IWebApplication;
import midgard.web.NanoHttp;
import midgard.web.Request;
import midgard.web.Response;
import midgard.web.URLHandler;
import midgard.web.URLView;

/**
 *
 * @author fenrrir
 */
public class FakeWebApp implements IWebApplication {
    private Vector views;

    public FakeWebApp() {
        views = new Vector();
        views.addElement(
                new URLView("/", new URLHandler(){
                        public Response serve(Request request) throws Exception{
                           return getHome();
                        }
                    }
                )
        );

        views.addElement(
                new URLView("/foobar", new URLHandler(){
                        public Response serve(Request request) throws Exception{
                           return getFoo();
                        }
                    }
                )
        );
    }

    public Vector getViews() {
        return views;
    }

    private Response getHome(){
        return getResponse("Hello World");
        
    }

    private Response getFoo(){
        return getResponse("FooBar");
    }

    private Response getResponse(String body){
        return new Response(NanoHttp.HTTP_OK, NanoHttp.MIME_HTML, body);
    }

}
