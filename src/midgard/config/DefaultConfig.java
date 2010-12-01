/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.config;

import midgard.componentmodel.IComponent;
import midgard.componentmodel.ProxyComponent;

/**
 *
 * @author fenrrir
 */
public class DefaultConfig extends ProxyComponent implements IDefaultConfig {
    private IDefaultConfig concreteComponent = null;

    public void setConcreteComponent(IComponent component) {
        super.setConcreteComponent(component);
        concreteComponent = (IDefaultConfig) component;
    }

    public long getSleepTime() {
        return concreteComponent.getSleepTime();
    }

}
