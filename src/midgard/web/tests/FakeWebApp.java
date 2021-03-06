/*
* Copyright (C) 2011 Rodrigo Pinheiro Marques de Araujo
*
* This program is free software; you can redistribute it and/or modify it under
* the terms of the GNU General Public License as published by the Free Software
* Foundation; either version 2 of the License, or (at your option) any later
* version.
*
* This program is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
* FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
* details.
*
* You should have received a copy of the GNU General Public License along with
* this program; if not, write to the Free Software Foundation, Inc., 51
* Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
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
