/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors;

import midgard.componentmodel.IComponent;
import midgard.componentmodel.ProxyComponent;

/**
 *
 * @author fenrrir
 */
public class ProxySensor extends ProxyComponent implements ISensor {
    private ISensor concreteComponent;

    public void setConcreteComponent(IComponent component) {
        super.setConcreteComponent(component);
        concreteComponent = (ISensor) component;
    }

    public void initSensor() {
        concreteComponent.initSensor();
    }

    public void disableSensor() {
        concreteComponent.disableSensor();
    }

    public void collect() {
        concreteComponent.collect();
    }
    




}
