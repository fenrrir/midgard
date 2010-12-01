/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.components;

import midgard.componentmodel.IComponent;
import midgard.componentmodel.IProxyComponent;
import midgard.services.IService;

/**
 *
 * @author fenrrir
 */
public interface IComponentManager extends IService, IComponentContainer {

    public void initializeComponent(IComponent component);
    public IComponent resolveComponent(String name);
    public void loadComponent(IComponent component);
    public void resumeComponent(IComponent component);
    public void pauseComponent(IComponent component);
    public void destroyComponent(IComponent component);
    public void changeImplementation(IProxyComponent proxy,
                                     IComponent old, 
                                     IComponent comp);
    public void changeImplementation(IProxyComponent proxy,
                                     IComponent comp);
    public void freeComponent(IComponent component);
}
