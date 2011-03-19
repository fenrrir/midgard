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

package midgard.events;

import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.services.ProxyService;

/**
 *
 * @author fenrrir
 */
public class EventManager extends ProxyService implements IEventManager {
    private IEventManager concreteComponent;

    public void setConcreteComponent(IComponent concreteComponent){
        super.setConcreteComponent(concreteComponent);
        this.concreteComponent = (IEventManager) concreteComponent;
    }

    public void resumeCustomEvent(ICustomEvent event) {
        concreteComponent.resumeCustomEvent(event);
    }

    public void resumeCustomEvent(String name) {
        concreteComponent.resumeCustomEvent(name);
    }

    public void pauseCustomEvent(ICustomEvent event) {
        concreteComponent.pauseCustomEvent(event);
    }

    public void pauseCustomEvent(String name) {
        concreteComponent.pauseCustomEvent(name);
    }

    public void loadAndInitializeCustomEvent(String name) {
        concreteComponent.loadAndInitializeCustomEvent(name);
    }

    public Vector listInstalledCustomEvents() {
        return concreteComponent.listInstalledCustomEvents();
    }

    public Vector listAllEvents() {
        return concreteComponent.listAllEvents();
    }

    public Vector listAllCustomEvents() {
        return concreteComponent.listAllCustomEvents();
    }

    public IEvent getEvent(String name) {
        return concreteComponent.getEvent(name);
    }

    public ICustomEvent getCustomEvent(String name) {
        return concreteComponent.getCustomEvent(name);
    }

    public void destroyCustomEvent(String name) {
        concreteComponent.destroyCustomEvent(name);
    }

    public void destroyCustomEvent(ICustomEvent event) {
        concreteComponent.destroyCustomEvent(event);
    }

}
