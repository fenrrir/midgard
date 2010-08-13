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
public class ProxyComponent implements IProxyComponent {

    
    private IComponent concreteComponent;

    public ProxyComponent(IComponent concreteComponent) {
        this.concreteComponent = concreteComponent;
    }


    public IComponent getConcreteComponent(){
        return concreteComponent;
    }

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



    public Vector getListeners() {
        return concreteComponent.getListeners();
    }



    public Hashtable getCacheFiredEvents() {
        return concreteComponent.getCacheFiredEvents();
    }

    public void fireEvent(IEvent event) {
        concreteComponent.fireEvent(event);
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



    public Hashtable getConnectedComponents() {
        return concreteComponent.getConnectedComponents();
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
