/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.components;

import midgard.componentmodel.IComponent;
import midgard.componentmodel.IProxyComponent;
import midgard.services.Service;

/**
 *
 * @author fenrrir
 */
public class DefaultComponentManager extends Service implements IComponentManager{

    public void changeImplementation(IProxyComponent proxy, IComponent old, IComponent comp) {
    }

    public void changeImplementation(IProxyComponent proxy, IComponent comp) {
    }

    public void destroyComponent(IComponent component) {
    }

    public void initializeComponent(IComponent component) {
    }

    public void loadComponent(IComponent component) {
    }

    public void pauseComponent(IComponent component) {
    }

    public void resumeComponent(IComponent component) {
    }

}
