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

package midgard.components;

import midgard.componentmodel.IComponent;
import midgard.componentmodel.IProxyComponent;
import midgard.services.IService;

/**
 *
 * @author fenrrir
 */
public interface IComponentManager extends IService, IComponentContainer {

    public void initializeComponent(IComponent component);
    public IComponent resolveComponent(String name);
    public void loadComponent(IComponent component);
    public void resumeComponent(IComponent component);
    public void pauseComponent(IComponent component);
    public void destroyComponent(IComponent component);
    public void changeImplementation(IProxyComponent proxy,
                                     IComponent old, 
                                     IComponent comp);
    public void changeImplementation(IProxyComponent proxy,
                                     IComponent comp);
    public void changeImplementation(String iface,
                                     String component);
    public void freeComponent(IComponent component);
}
