/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

import midgard.componentmodel.IComponent;
import midgard.events.ICustomEvent;

/**
 *
 * @author fenrrir
 */
public class EventRepositoryManager extends RepositoryManager  implements IEventRepositoryManager{
    private IEventRepositoryManager concreteComponent;



    public void setConcreteComponent(IComponent concreteComponent){
        super.setConcreteComponent(concreteComponent);
        this.concreteComponent = (IEventRepositoryManager) concreteComponent;
    }

    public ICustomEvent getEvent(String name) {
        return concreteComponent.getEvent(name);
    }


}
