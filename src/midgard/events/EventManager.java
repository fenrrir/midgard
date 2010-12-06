/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
