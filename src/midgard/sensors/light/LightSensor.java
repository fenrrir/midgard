/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors.light;

import midgard.sensors.light.ILightSensor;
import midgard.componentmodel.IComponent;
import midgard.componentmodel.ProxyComponent;
import midgard.sensors.ProxySensor;

/**
 *
 * @author fenrrir
 */
public class LightSensor extends ProxySensor implements ILightSensor{
    private ILightSensor concreteComponent;
    
    public void setConcreteComponent(IComponent component) {
        super.setConcreteComponent(component);
        concreteComponent = (ILightSensor) concreteComponent;
    }

    public void enableThresholds(int low, int high) {
        concreteComponent.enableThresholds(low, high);
    }

    public void disableThresholds() {
        concreteComponent.disableThresholds();
    }



}
