/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midgard.sensors.light;

import com.sun.spot.sensorboard.EDemoBoard;
import com.sun.spot.sensorboard.peripheral.ILightSensor;
import com.sun.spot.sensorboard.peripheral.ILightSensorThresholdListener;
import java.io.IOException;
import midgard.app.IAppRepositoryManager;
import midgard.componentmodel.IComponent;
import midgard.sensors.Sensor;
import midgard.sensors.events.LightEvent;
import midgard.sensors.events.ThresholdChangedLightEvent;
import midgard.sensors.events.ThresholdExceededLightEvent;

/**
 *
 * @author fenrrir
 */
public class DefaultLightSensor extends Sensor implements midgard.sensors.light.ILightSensor, ILightSensorThresholdListener {

    private IAppRepositoryManager appRepositoryManager;
    private com.sun.spot.sensorboard.peripheral.ILightSensor lightSensor;

    public void connect(String interfaceName, IComponent component) {
        super.connect(interfaceName, component);
        if (interfaceName.equals(IAppRepositoryManager.class.getName())){
            appRepositoryManager = (IAppRepositoryManager) component;
        }
    }

    public String[] getRequiredInterfaces() {
        return new String [] {IAppRepositoryManager.class.getName()};
    }



    public void collect() {
        try {
            int lightLevel = lightSensor.getValue();
            System.err.println("LightSensor.getValue() = " + lightLevel);
            fireEvent(new LightEvent(new Integer(lightLevel)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Callback when low or high light threshold is crossed.
     *
     * @param light the light sensor
     * @param val current light sensor value
     */
    public void thresholdExceeded(ILightSensor light, int val) {
        String which = (val < 100) ? "Low" : "Bright";
        System.err.println(which + " light level event: light level = " + val);
        lightSensor.enableThresholdEvents(true);            // re-enable notification
        fireEvent(new ThresholdExceededLightEvent(new Integer(val)));
    }

    /**
     * Callback when anyone changes the light sensor thresholds.
     *
     * @param light the light sensor
     * @param low cause a threshold event when light sensor value is less than or equal low
     * @param high cause a threshold event when light sensor value is greater than or equal high
     */
    public void thresholdChanged(ILightSensor light, int low, int high) {
        System.err.println("thresholdChanged " + light + " " + low + " " + high);
        Object content = new ThresholdChangedLightData(low, high);
        fireEvent( new ThresholdChangedLightEvent(content) );
    }

    public void initSensor(){
        int high, low;
        ThresholdChangedLightData thresholdChangedLightData;
        lightSensor = EDemoBoard.getInstance().getLightSensor();

        thresholdChangedLightData = appRepositoryManager.getLightThreshold();
        high = thresholdChangedLightData.high;
        low = thresholdChangedLightData.low;
        if (!( high == low && low == -1 )){
            lightSensor.addILightSensorThresholdListener(this); // register us as a listener
            lightSensor.setThresholds(low, high);   
            lightSensor.enableThresholdEvents(true);            // turn on notification
        }
        else{
            System.err.println("Light threshold not seted");
        }
    }

    public void disableSensor(){
        lightSensor.removeILightSensorThresholdListener(this);
        lightSensor.enableThresholdEvents(false);
        lightSensor = null;
    }

}
