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

package midgard.repositories.events;

import midgard.repositories.events.IEventRepositoryManager;
import java.util.Vector;
import midgard.componentmodel.Component;
import midgard.componentmodel.IComponent;
import midgard.components.IComponentManager;
import midgard.components.IComponentRepositoryManager;
import midgard.events.ICustomEvent;
import midgard.events.IEvent;

/**
 *
 * @author fenrrir
 */
public class DefaultEventRepositoryManager extends Component implements IEventRepositoryManager {

    private IComponentRepositoryManager repository;
    private IComponentManager componentManager;

    public void initialize() {
        super.initialize();
        repository = (IComponentRepositoryManager)
                getConnectedComponents()
                    .get(IComponentRepositoryManager.class.getName());

        componentManager = (IComponentManager)
                getConnectedComponents()
                    .get(IComponentManager.class.getName());
    }

    public String[] getRequiredInterfaces() {
        return new String[]{
            IComponentRepositoryManager.class.getName(),
            IComponentManager.class.getName()

        };
    }


    public void clear() {
    }

    public void close() {
    }

    public void open() {
    }

    public IComponent get(String name) {
        return componentManager.resolveComponent(name);
    }

    public ICustomEvent getEvent(String name){
        return (ICustomEvent) get(name);
    }

    public Vector list() {
        return repository.getComponentsFromInterface(IEvent.class.getName());
    }

    public Vector listCustomEvents() {
        return repository.getComponentsFromInterface(ICustomEvent.class.getName());
    }

    public Vector listEvents() {
        return list();
    }

}
