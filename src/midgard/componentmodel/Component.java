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

    public Hashtable getConnectedComponents() {
        if (components == null)
            components = new Hashtable();
        return components;
    }


    public Hashtable getCacheFiredEvents() {
        if (events == null)
            events = new Hashtable();
        return events;
    }

    public Vector getListeners() {
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
        if (getConnectedComponents().containsKey(interfaceName)){
            v = (Vector) getConnectedComponents().get(interfaceName);
            v.addElement(component);
        }else{
            v = new Vector();
            v.addElement(component);
            getConnectedComponents().put(interfaceName, v);
        }
    }

    public void disconnect(String interfaceName,  IComponent component) {
        if (getConnectedComponents().containsKey(interfaceName)){
            Vector v = (Vector) getConnectedComponents().get(interfaceName);
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
        if (getCacheFiredEvents().containsKey(new Float(event.getType()))){
            return (Vector) getCacheFiredEvents().get(new Float ( event.getType()));
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
        if (getCacheFiredEvents().containsKey(new Float(event.getType()))){
            v = (Vector) getCacheFiredEvents().get(event);
            if (getCacheFiredEvents().size()  < 5){
                v.addElement(event);
            }
            
        }else{
            v = new Vector();
            getCacheFiredEvents().put(new Float(event.getType()), v);
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
