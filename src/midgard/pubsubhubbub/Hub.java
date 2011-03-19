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

package midgard.pubsubhubbub;

import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.services.ProxyService;
import midgard.web.Request;
import midgard.web.Response;

/**
 *
 * @author fenrrir
 */
public class Hub extends ProxyService implements IHub {
    private IHub concreteComponent;

    public void setConcreteComponent(IComponent component){
        super.setConcreteComponent(component);
        this.concreteComponent = (IHub) concreteComponent;

    }

    public Vector getURIs() {
        return concreteComponent.getURIs();
    }

    public Response serve(Request request) throws Exception {
        return concreteComponent.serve(request);
    }

}
