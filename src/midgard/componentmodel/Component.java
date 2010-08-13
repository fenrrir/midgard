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
public class Component implements IComponent {
    private Hashtable components, events, params;
    private Vector listeners;
    private String[] requiredInterfaces = {};
    private String[] providedInterfaces = {};

    public Component() {
        components = null;
        events = null;
        listeners = null;
        params = null;
    }

    private Hashtable getComponents() {
        if (components == null)
            components = new Hashtable();
        return components;
    }


    private Hashtable getEvents() {
        if (events == null)
            events = new Hashtable();
        return events;
    }

    private Vector getListeners() {
        if (listeners == null)
            listeners = new Vector();
        return listeners;
    }

    private Hashtable getParams() {
        if (params == null)
            params = new Hashtable();
        return params;
    }



    public void connect(String interfaceName, IComponent component) {
        Vector v;
        if (getComponents().containsKey(interfaceName)){
            v = (Vector) getComponents().get(interfaceName);
            v.addElement(component);
        }else{
            v = new Vector();
            v.addElement(component);
            getComponents().put(interfaceName, v);
        }
    }

    public void disconnect(String interfaceName,  IComponent component) {
        if (getComponents().containsKey(interfaceName)){
            Vector v = (Vector) getComponents().get(interfaceName);
            v.removeElement(component);
        }
    }

    public String[] getProvidedInterfaces() {
        return providedInterfaces;
    }

    public String[] getRequiredInterfaces() {
        return requiredInterfaces;
    }

    public Vector getEventHistory(IEvent event) {
        if (getEvents().containsKey(new Float(event.getType()))){
            return (Vector) getEvents().get(new Float ( event.getType()));
        }
        return new Vector();
   
    }

    public void registerEventListener(IListener listener) {
        getListeners().addElement(listener);
    }

    public void removeEventListener(IListener listener) {
        getListeners().removeElement(listener);
    }

    public void fireEvent(IEvent event) {
        Vector v;
        if (getEvents().containsKey(new Float(event.getType()))){
            v = (Vector) getEvents().get(event);
            if (getEvents().size()  < 5){
                v.addElement(event);
            }
            
        }else{
            v = new Vector();
            getEvents().put(new Float(event.getType()), v);
            v.addElement(event);

        }


        IListener listener;
        for (int i=0; i < getListeners().size(); i++){
            listener = (IListener) getListeners().elementAt(i);
            listener.newEventArrived(event);
        }
    }

    public void destroy() {
    }

    public void initialize() {
    }

    public void load() {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void newEventArrived(IEvent event) {
    }

    public Object getConfigurationParameter(String name) {
        return getParams().get(name);
    }

    public Hashtable getConfigurationParameters() {
        return getParams();
    }

    public void setConfigurationParameter(String name, Object value) {
        getParams().put(name, value);
    }

    public void setConfigurationParameters(Hashtable params) {
        this.params = params;
    }






}
