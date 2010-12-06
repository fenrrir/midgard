/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midgard.events;

import java.util.Vector;
import midgard.app.IAppManager;
import midgard.app.IAppRepositoryManager;
import midgard.componentmodel.IComponent;
import midgard.components.IComponentManager;
import midgard.repositories.IEventRepositoryManager;
import midgard.services.Service;

/**
 *
 * @author fenrrir
 */
public class DefaultEventManager extends Service implements IEventManager {

    private IComponentManager componentManager;
    private IAppRepositoryManager appRepository;
    private IEventRepositoryManager repository;

    public void startService() {
        super.startService();


        Vector events = listInstalledCustomEvents();
        String eventName;

        for (int i = 0; i < events.size(); i++) {
            eventName = (String) events.elementAt(i);
            loadAndInitializeCustomEvent(eventName);
        }

    }

    public void stopService() {
        super.stopService();

        Vector events = listInstalledCustomEvents();
        String eventName;

        for (int i = 0; i < events.size(); i++) {
            eventName = (String) events.elementAt(i);
            destroyCustomEvent(eventName);
        }
    }

    public String[] getRequiredInterfaces() {
        return new String[]{
                    IComponentManager.class.getName(),
                    IAppRepositoryManager.class.getName(),
                    IEventRepositoryManager.class.getName()
                };
    }

    public void initialize() {
        super.initialize();
        appRepository = (IAppRepositoryManager) getConnectedComponents().get(IAppRepositoryManager.class.getName());
        componentManager = (IComponentManager) getConnectedComponents().get(IComponentManager.class.getName());
        repository = (IEventRepositoryManager) getConnectedComponents().get(IEventRepositoryManager.class.getName());
    }

    public void destroyCustomEvent(ICustomEvent event) {
        Vector components;
        IComponent component;

        components = event.connectIn();
        for (int i = 0; i < components.size(); i++) {
            component = componentManager.resolveComponent((String) components.elementAt(i));
            component.removeEventListener(event);
        }

        componentManager.destroyComponent(event);
    }

    public void destroyCustomEvent(String name) {
        ICustomEvent event = getCustomEvent(name);
        destroyCustomEvent(event);
    }

    public ICustomEvent getCustomEvent(String name) {
        return (ICustomEvent) componentManager.resolveComponent(name);
    }

    public IEvent getEvent(String name) {
        return (IEvent) componentManager.resolveComponent(name);
    }

    public Vector listAllCustomEvents() {
        return repository.listCustomEvents();
    }

    public Vector listAllEvents() {
        return repository.listEvents();
    }

    public Vector listInstalledCustomEvents() {
        return appRepository.listCustomEvents();
    }

    public void loadAndInitializeCustomEvent(String name) {
        ICustomEvent event = getCustomEvent(name);

        Vector components;
        IComponent component;

        components = event.connectIn();
        for (int i = 0; i < components.size(); i++) {
            component = componentManager.resolveComponent((String) components.elementAt(i));
            component.registerEventListener(event);
        }

    }

    public void pauseCustomEvent(String name) {
        ICustomEvent event = getCustomEvent(name);
        pauseCustomEvent(event);
    }

    public void pauseCustomEvent(ICustomEvent event) {
        pauseCustomEvent(event);
    }

    public void resumeCustomEvent(String name) {
        ICustomEvent event = getCustomEvent(name);
        resumeCustomEvent(event);
    }

    public void resumeCustomEvent(ICustomEvent event) {
        resumeCustomEvent(event);
    }
}
