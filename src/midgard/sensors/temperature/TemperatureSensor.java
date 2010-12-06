/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors.temperature;

import midgard.componentmodel.IComponent;
import midgard.componentmodel.ProxyComponent;
import midgard.sensors.ProxySensor;

/**
 *
 * @author fenrrir
 */
public class TemperatureSensor extends ProxySensor implements ITemperatureSensor {
    private ITemperatureSensor concreteComponent;

    public void setConcreteComponent(IComponent component) {
        super.setConcreteComponent(component);
        concreteComponent = (ITemperatureSensor) concreteComponent;
    }


    public void enableThresholds(double low, double high, boolean celsius) {
        concreteComponent.enableThresholds(low, high, celsius);
    }

    public void disableThresholds() {
        concreteComponent.disableThresholds();
    }

    

}
