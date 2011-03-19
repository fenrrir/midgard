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
import midgard.components.IComponentContainer;
import midgard.repositories.RepositoryManager;

/**
 *
 * @author fenrrir
 */
public class ComponentRepositoryManager extends RepositoryManager implements IComponentRepositoryManager, IComponentContainer{
    private IComponentRepositoryManager concreteComponent;

    public void setConcreteComponent(IComponent concreteComponent){
        super.setConcreteComponent(concreteComponent);
        this.concreteComponent = (IComponentRepositoryManager) concreteComponent;
    }

    public static ComponentRepositoryManager getDefault(){
        ComponentRepositoryManager manager = new ComponentRepositoryManager();
        manager.setConcreteComponent(new DefaultComponentRepositoryManager());
        return manager;
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

    public IProxyComponent getProxyOf(String name) {
        return concreteComponent.getProxyOf(name);
    }

    public void freeComponent(String name) {
        concreteComponent.freeComponent(name);
    }

    public Vector getOnMemoryComponents() {
        return concreteComponent.getOnMemoryComponents();
    }

    public Vector getOnMemoryComponentNames() {
        return concreteComponent.getOnMemoryComponentNames();
    }



}
