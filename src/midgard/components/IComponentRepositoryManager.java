/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.components;

import midgard.componentmodel.IProxyComponent;
import midgard.components.IComponentContainer;
import midgard.repositories.IRepositoryManager;

/**
 *
 * @author fenrrir
 */
public interface IComponentRepositoryManager extends IRepositoryManager, IComponentContainer {
    public IProxyComponent getProxyOf(String name);

}
