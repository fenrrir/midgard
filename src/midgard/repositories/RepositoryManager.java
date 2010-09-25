/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.componentmodel.ProxyComponent;

/**
 *
 * @author fenrrir
 */
public abstract class RepositoryManager extends ProxyComponent implements IRepositoryManager{
    private IRepositoryManager concreteComponent;

    public RepositoryManager() {
    }

    

    public Vector list() {
        return concreteComponent.list();
    }

    public IComponent get(String name) {
        return concreteComponent.get(name);
    }

    public void setConcreteComponent(IComponent concreteComponent){
        super.setConcreteComponent(concreteComponent);
        this.concreteComponent = (IRepositoryManager) concreteComponent;
    }

    public void open() {
        concreteComponent.open();
    }

    public void close() {
        concreteComponent.close();
    }

    public void clear() {
        concreteComponent.clear();
    }



}
