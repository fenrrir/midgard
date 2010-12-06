/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

import java.util.Vector;
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

    public Vector listEvents() {
        return concreteComponent.listEvents();
    }

    public Vector listCustomEvents() {
        return concreteComponent.listCustomEvents();
    }

    


}
