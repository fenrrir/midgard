/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.componentmodel.IProxyComponent;
import midgard.components.IComponentContainer;

/**
 *
 * @author fenrrir
 */
public class ComponentRepositoryManager extends RepositoryManager implements IComponentRepositoryManager, IComponentContainer{
    private IComponentRepositoryManager concreteComponent;

    public ComponentRepositoryManager(IComponentRepositoryManager concreteComponent) {
        super(concreteComponent);
        this.concreteComponent = concreteComponent;
    }

    public void setConcreteComponent(IComponentRepositoryManager concreteComponent){
        super.setConcreteComponent(concreteComponent);
        this.concreteComponent = concreteComponent;
    }

    public static ComponentRepositoryManager getDefault(){
        return new ComponentRepositoryManager(new DefaultComponentRepositoryManager());
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
