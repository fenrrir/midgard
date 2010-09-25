/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.components;

import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.componentmodel.IProxyComponent;
import midgard.components.IComponentContainer;
import midgard.repositories.RepositoryManager;

/**
 *
 * @author fenrrir
 */
public class ComponentRepositoryManager extends RepositoryManager implements IComponentRepositoryManager, IComponentContainer{
    private IComponentRepositoryManager concreteComponent;

    public void setConcreteComponent(IComponent concreteComponent){
        super.setConcreteComponent(concreteComponent);
        this.concreteComponent = (IComponentRepositoryManager) concreteComponent;
    }

    public static ComponentRepositoryManager getDefault(){
        ComponentRepositoryManager manager = new ComponentRepositoryManager();
        manager.setConcreteComponent(new DefaultComponentRepositoryManager());
        return manager;
    }

    public Vector getInterfaceNames() {
        return concreteComponent.getInterfaceNames();
    }

    public IComponent getImplementationOfInterface(String name) {
        return concreteComponent.getImplementationOfInterface(name);
    }

    public Vector getComponentsFromInterface(String name) {
        return concreteComponent.getComponentsFromInterface(name);
    }

    public Vector getComponentNames() {
        return concreteComponent.getComponentNames();
    }

    public IComponent getComponent(String name) {
        return concreteComponent.getComponent(name);
    }

    public IProxyComponent getProxyOf(String name) {
        return concreteComponent.getProxyOf(name);
    }

    
}
