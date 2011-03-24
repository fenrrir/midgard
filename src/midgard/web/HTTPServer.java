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

package midgard.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import midgard.componentmodel.IComponent;
import midgard.componentmodel.ProxyComponent;

/**
 *
 * @author fenrrir
 */
public class HTTPServer extends ProxyComponent implements IHTTPServer{
    private IHTTPServer concreteComponent;

    public void setConcreteComponent(IComponent component){
        super.setConcreteComponent(component);
        concreteComponent = (IHTTPServer) component;
    }

    public String handleRequest(String request){
        return concreteComponent.handleRequest(request);
    }

    public void addView(URLView view) {
        concreteComponent.addView(view);
    }

    public void removeViewWithURI(String URI) {
        concreteComponent.removeViewWithURI(URI);
    }

}
