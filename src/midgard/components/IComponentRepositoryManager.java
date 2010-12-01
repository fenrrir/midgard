/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.components;

import java.util.Vector;
import midgard.componentmodel.IProxyComponent;
import midgard.repositories.IRepositoryManager;

/**
 *
 * @author fenrrir
 */
public interface IComponentRepositoryManager extends IRepositoryManager, IComponentContainer {
    public IProxyComponent getProxyOf(String name);
    public Vector getOnMemoryComponentNames();
    public Vector getOnMemoryComponents();

}
