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

import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.services.ProxyService;

/**
 *
 * @author fenrrir
 */
public class WebServer extends ProxyService implements IWebServer{
    private IWebServer concreteComponent;


    public void setConcreteComponent(IComponent component){
        super.setConcreteComponent(component);
        concreteComponent = (IWebServer) component;
    }

    public void run() {
        concreteComponent.run();
    }

    public void removeWebApplication(IWebApplication application) {
        concreteComponent.removeWebApplication(application);
    }

    public Vector listWebApplications() {
        return concreteComponent.listWebApplications();
    }

    public void addWebApplication(IWebApplication application) {
        concreteComponent.addWebApplication(application);
    }

}
