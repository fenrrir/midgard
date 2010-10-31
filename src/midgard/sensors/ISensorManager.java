/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors;

import midgard.sensors.accelerometer.IAccelerometerSensor;
import midgard.sensors.battery.IBatterySensor;
import midgard.sensors.light.ILightSensor;
import midgard.sensors.network.INetworkSensor;
import midgard.sensors.temperature.ITemperatureSensor;
import midgard.services.IService;

/**
 *
 * @author fenrrir
 */
public interface ISensorManager extends IService, ISensor, Runnable {

    public IAccelerometerSensor getAccelerometerSensor();

    public IBatterySensor getBatterySensor();

    public ILightSensor getLight();

    public INetworkSensor getNetworkSensor();

    public ITemperatureSensor getThemperatureSensor();

}
