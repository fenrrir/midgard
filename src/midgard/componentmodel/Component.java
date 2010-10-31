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
    private boolean  loaded, initialized, paused;
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

    public boolean isInitialized() {
        return initialized;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public boolean isPaused() {
        return paused;
    }

    

    public String getName(){
        return this.getClass().getName();
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
        if (!getConnectedComponents().containsKey(interfaceName)){
            getConnectedComponents().put(interfaceName, component);
        }
    }

    public void disconnect(String interfaceName,  IComponent component) {
        if (getConnectedComponents().containsKey(interfaceName)){
            getConnectedComponents().remove(interfaceName);
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
        Float key = new Float(event.getType());
        if (getCacheFiredEvents().containsKey(key)){
            v = (Vector) getCacheFiredEvents().get(key);
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
        initialized = loaded = false;

    }

    public void initialize() {
        initialized = true;
    }

    public void load() {
        loaded = true;
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
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
