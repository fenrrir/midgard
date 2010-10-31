/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors;

import midgard.componentmodel.IComponent;
import midgard.sensors.accelerometer.IAccelerometerSensor;
import midgard.sensors.temperature.ITemperatureSensor;
import midgard.sensors.battery.IBatterySensor;
import midgard.sensors.network.INetworkSensor;
import midgard.sensors.light.ILightSensor;
import midgard.services.ProxyService;

/**
 *
 * @author fenrrir
 */
public class SensorManager extends ProxyService implements ISensorManager{
    private ISensorManager concreteComponent;

    public void setConcreteComponent(IComponent component) {
        super.setConcreteComponent(component);
        this.concreteComponent = (ISensorManager) concreteComponent;
    }

    public ITemperatureSensor getThemperatureSensor() {
        return concreteComponent.getThemperatureSensor();
    }

    public INetworkSensor getNetworkSensor() {
        return concreteComponent.getNetworkSensor();
    }

    public ILightSensor getLight() {
        return concreteComponent.getLight();
    }

    public IBatterySensor getBatterySensor() {
        return concreteComponent.getBatterySensor();
    }

    public IAccelerometerSensor getAccelerometerSensor() {
        return concreteComponent.getAccelerometerSensor();
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

    public void run() {
        concreteComponent.run();
    }
   
}
