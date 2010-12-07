/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors.accelerometer;

import midgard.componentmodel.IComponent;
import midgard.sensors.ProxySensor;



/**
 *
 * @author fenrrir
 */
public class AccelerometerSensor extends ProxySensor implements IAccelerometerSensor{
    private IAccelerometerSensor concreteComponent;

    public void setConcreteComponent(IComponent component) {
        super.setConcreteComponent(component);
        concreteComponent = (IAccelerometerSensor) component;
    }



    public void enableThresholds(AccelerometerThresholds thresholds) {
        concreteComponent.enableThresholds(thresholds);
    }

    public void disableThresholds() {
        concreteComponent.disableThresholds();
    }


}
