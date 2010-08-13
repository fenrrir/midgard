/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.componentmodel;

import java.util.Hashtable;
import java.util.Vector;

import midgard.events.IEvent;
import midgard.events.IListener;

/**
 *
 * @author fenrrir
 */
public class ProxyComponent implements IComponent {
    
    private IComponent concreteComponent;

    public void newEventArrived(IEvent event) {
        concreteComponent.newEventArrived(event);
    }

    public void removeEventListener(IListener listener) {
        concreteComponent.removeEventListener(listener);
    }

    public void registerEventListener(IListener listener) {
        concreteComponent.registerEventListener(listener);
    }

    public Vector getEventHistory(IEvent event) {
        return concreteComponent.getEventHistory(event);
    }

    public void fireEvent(IEvent event) {
        concreteComponent.fireEvent(event);
    }

    public ProxyComponent(IComponent concreteComponent) {
        this.concreteComponent = concreteComponent;
    }

    public String[] getRequiredInterfaces() {
        return concreteComponent.getRequiredInterfaces();
    }

    public String[] getProvidedInterfaces() {
        return concreteComponent.getProvidedInterfaces();
    }

    public void disconnect(String interfaceName, IComponent component) {
        concreteComponent.disconnect(interfaceName, component);
    }

    public void connect(String interfaceName, IComponent component) {
        concreteComponent.connect(interfaceName, component);
    }

    public void resume() {
        concreteComponent.resume();
    }

    public void pause() {
        concreteComponent.pause();
    }

    public void load() {
        concreteComponent.load();
    }

    public void initialize() {
        concreteComponent.initialize();
    }

    public void destroy() {
        concreteComponent.destroy();
    }

    public void setConfigurationParameters(Hashtable params) {
        concreteComponent.setConfigurationParameters(params);
    }

    public void setConfigurationParameter(String name, Object value) {
        concreteComponent.setConfigurationParameter(name, value);
    }

    public Hashtable getConfigurationParameters() {
        return concreteComponent.getConfigurationParameters();
    }

    public Object getConfigurationParameter(String name) {
        return concreteComponent.getConfigurationParameter(name);
    }



}
