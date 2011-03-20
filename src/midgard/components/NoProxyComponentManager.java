/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.components;

import midgard.componentmodel.IComponent;
import midgard.kernel.ClassLoader;

/**
 *
 * @author fenrrir
 */
public class NoProxyComponentManager extends DefaultComponentManager {

    public IComponent resolveComponent(String name) {
        IComponent component;
        if (ClassLoader.isInterface(name)) {
            component = getImplementationOfInterface(name);
            loadAndInitializeComponent(component);
            return component;

        }
        return getComponent(name);
    }
}
