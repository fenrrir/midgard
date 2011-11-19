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
        loaded = false;
        paused = false;
        initialized = false;
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
        Vector listeners = getListeners();
        if (!listeners.contains(listener))
            listeners.addElement(listener);
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
