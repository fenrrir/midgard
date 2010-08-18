/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

import midgard.componentmodel.IProxyComponent;
import midgard.components.IComponentContainer;

/**
 *
 * @author fenrrir
 */
public interface IComponentRepositoryManager extends IRepositoryManager, IComponentContainer {
    public IProxyComponent getProxyOf(String name);

}
