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

import midgard.componentmodel.IComponent;
import midgard.events.IListener;
import midgard.services.ProxyService;

/**
 *
 * @author fenrrir
 */
public class Subscriber extends ProxyService implements ISubscriber {
    private ISubscriber concreteComponent;

    public void setConcreteComponent( IComponent component ){
        super.setConcreteComponent(component);
        this.concreteComponent = (ISubscriber) component;
    }

    public void unRegister(IListener listener, String topic) {
        concreteComponent.unRegister(listener, topic);
    }

    public void register(IListener listener, String topic) {
        concreteComponent.register(listener, topic);
    }

    public void register(IListener listener, String[] topics, String address) {
        concreteComponent.register(listener, topics, address);
    }

    public void unRegister(IListener listener, String topic, String address) {
        concreteComponent.unRegister(listener, topic, address);
    }

    public void register(IListener listener, String topic, String address) {
        concreteComponent.register(listener, topic, address);
    }

    
}
