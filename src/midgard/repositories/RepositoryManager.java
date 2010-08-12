/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

import midgard.componentmodel.IComponent;
import midgard.componentmodel.ProxyComponent;

/**
 *
 * @author fenrrir
 */
public abstract class RepositoryManager extends ProxyComponent implements IRepositoryManager{
    private IRepositoryManager concreteComponent;

    public RepositoryManager(IRepositoryManager concreteComponent) {
        super((IComponent) concreteComponent);
        this.concreteComponent = concreteComponent;
    }

}
