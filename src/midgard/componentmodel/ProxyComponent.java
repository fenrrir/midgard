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
public class ProxyComponent implements IProxyComponent {

    
    private IComponent concreteComponent = null;

    public ProxyComponent(){
    }

    public ProxyComponent(IComponent concreteComponent) {
        this.concreteComponent = concreteComponent;
    }

    public void setConcreteComponent(IComponent component){
        this.concreteComponent = component;
    }


    public IComponent getConcreteComponent(){
        return concreteComponent;
    }

    public String getName(){
        return this.getClass().getName();
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

    public boolean isPaused() {
        if (concreteComponent != null)
            return concreteComponent.isPaused();
        return false;
    }

    public boolean isLoaded() {
        if (concreteComponent != null)
            return concreteComponent.isLoaded();
        return false;
    }

    public boolean isInitialized() {
        if (concreteComponent != null)
            return concreteComponent.isInitialized();
        return false;
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
