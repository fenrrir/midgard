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

import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.componentmodel.IProxyComponent;
import midgard.services.IService;
import midgard.services.ProxyService;

/**
 *
 * @author fenrrir
 */
public class ComponentManager extends ProxyService implements IComponentManager {
    private IComponentManager concreteComponent;

    public ComponentManager() {
    }



    public void setConcreteComponent(IComponent concreteComponent){
        super.setConcreteComponent(concreteComponent);
        this.concreteComponent = (IComponentManager) concreteComponent;
    }

    public void resumeComponent(IComponent component) {
        concreteComponent.resumeComponent(component);
    }

    public void pauseComponent(IComponent component) {
        concreteComponent.pauseComponent(component);
    }

    public void loadComponent(IComponent component) {
        concreteComponent.loadComponent(component);
    }

    public void initializeComponent(IComponent component) {
        concreteComponent.initializeComponent(component);
    }

    public void destroyComponent(IComponent component) {
        concreteComponent.destroyComponent(component);
    }

    public void changeImplementation(IProxyComponent proxy, IComponent comp) {
        concreteComponent.changeImplementation(proxy, comp);
    }

    public void changeImplementation(IProxyComponent proxy, IComponent old, IComponent comp) {
        concreteComponent.changeImplementation(proxy, old, comp);
    }

    public IComponent resolveComponent(String name) {
        return concreteComponent.resolveComponent(name);
    }
    


    public Vector getInterfaceNames() {
        return concreteComponent.getInterfaceNames();
    }

    public IComponent getImplementationOfInterface(String name) {
        return concreteComponent.getImplementationOfInterface(name);
    }

    public Vector getComponentsFromInterface(String name) {
        return concreteComponent.getComponentsFromInterface(name);
    }

    public Vector getComponentNames() {
        return concreteComponent.getComponentNames();
    }

    public IComponent getComponent(String name) {
        return concreteComponent.getComponent(name);
    }

    public void freeComponent(String name) {
        concreteComponent.freeComponent(name);
    }

    public void freeComponent(IComponent component) {
        concreteComponent.freeComponent(component);
    }

    public void changeImplementation(String iface, String component) {
        concreteComponent.changeImplementation(iface, component);
    }

    
}
