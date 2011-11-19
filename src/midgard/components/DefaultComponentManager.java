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
package midgard.components;

import java.util.Hashtable;
import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.componentmodel.IProxyComponent;
import midgard.kernel.ClassLoader;
import midgard.services.Service;

/**
 *
 * @author fenrrir
 */
public class DefaultComponentManager extends Service implements IComponentManager {

    private IComponentRepositoryManager repository = null;
    private Hashtable requestComponent; // for loop safe

    public void initialize() {
        super.initialize();
        requestComponent = new Hashtable();
        repository = (IComponentRepositoryManager) getConnectedComponents().get(IComponentRepositoryManager.class.getName());
    }

    private void setRequestComponent(String name) {
        requestComponent.put(Thread.currentThread(), name);
    }

    private String getRequestComponent() {
        if (requestComponent.containsKey(Thread.currentThread())) {
            return (String) requestComponent.get(Thread.currentThread());
        } else {
            return "";
        }
    }

    public void destroy() {
        super.destroy();
        requestComponent.clear();

    }

    public void pause() {
        super.pause();
    }

    public String[] getRequiredInterfaces() {
        return new String[]{IComponentRepositoryManager.class.getName()};
    }

    public void changeImplementation(IProxyComponent proxy, IComponent old, IComponent comp) {
        changeImplementation(proxy, comp);
    }

    public void changeImplementation(String iface, String component) {
        IProxyComponent proxy = (IProxyComponent) resolveComponent(iface);
        freeComponent(proxy.getConcreteComponent());
        IComponent newComponent = resolveComponent(component);
        proxy.setConcreteComponent(newComponent);
    }



    public void changeImplementation(IProxyComponent proxy, IComponent comp) {
        proxy.pause();
        proxy.destroy();
        freeComponent(proxy.getConcreteComponent());
        proxy.setConcreteComponent(comp);
    }

    public void destroyComponent(IComponent component) {
        component.destroy();
        freeComponent(component);
    }

    public void initializeComponent(IComponent component) {
        if (!component.isInitialized()) {
            //System.err.println(getName() + " initialize");
            component.initialize();
            //System.err.println(getName() + " posInitilized");
        }
    }

    public void loadComponent(IComponent component) {     
        if (!component.isLoaded()) {
            //System.err.println(getName() + " load");
            component.load();
            //System.err.println(getName() + " posLoad");
        }
    }

    protected void loadAndInitializeComponent(IComponent component) {
        String[] requires;
        IComponent dependency = null;

        if (getRequestComponent().equals("")) // for loop detection
        {
            setRequestComponent(component.getName());
        }

        loadComponent(component);


        if (!component.isInitialized()) {
            requires = component.getRequiredInterfaces();

            for (int i = 0; i < requires.length; i++) {
                String name = requires[i];

                if (name.equals(getRequestComponent())) {
                    // loop
                    dependency = getComponent(name, false);
                } else {
                    dependency = resolveComponent(name);
                }
                component.connect(name, dependency);
            }
            initializeComponent(component);
        }

        if (getRequestComponent().equals(component.getName())) { // configuration ended
            setRequestComponent("");
        }
    }

    public void pauseComponent(IComponent component) {
        if (!component.isPaused()) {
            component.pause();
        }
    }

    public void resumeComponent(IComponent component) {
        if (component.isPaused()) {
            component.resume();
        }
    }

    public Vector getInterfaceNames() {
        return repository.getInterfaceNames();
    }

    public IComponent getImplementationOfInterface(String name) {
        return repository.getImplementationOfInterface(name);
    }

    public Vector getComponentsFromInterface(String name) {
        return repository.getComponentsFromInterface(name);
    }

    public Vector getComponentNames() {
        return repository.getComponentNames();
    }

    private IComponent getComponent(String name, boolean initialize) {
        IComponent component = repository.getComponent(name);
        if (initialize) {
            loadAndInitializeComponent(component);
        }
        return component;
    }

    public IComponent getComponent(String name) {
        return getComponent(name, true);
    }

    public IComponent resolveComponent(String name) {
        IComponent component;
        if (ClassLoader.isInterface(name)) {
            IProxyComponent proxy = repository.getProxyOf(name);
            component = getImplementationOfInterface(name);
            proxy.setConcreteComponent(component); //TODO: test this
            loadAndInitializeComponent(proxy);
            return proxy;

        }
        return getComponent(name);
    }

    public void freeComponent(String name) {
        IComponent component = getComponent(name, false);
        freeComponent(component);
        
    }

    public void freeComponent(IComponent component) {
        component.pause();
        component.destroy();
        repository.freeComponent(component.getName());
    }





}
