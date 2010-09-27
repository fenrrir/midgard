/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midgard.sensors;

import com.sun.spot.sensorboard.EDemoBoard;
import com.sun.spot.sensorboard.peripheral.ILightSensor;
import com.sun.spot.sensorboard.peripheral.ILightSensorThresholdListener;
import java.io.IOException;
import midgard.sensors.events.LightEvent;
import midgard.sensors.events.ThresholdChangedLightEvent;
import midgard.sensors.events.ThresholdExceededLightEvent;

/**
 *
 * @author fenrrir
 */
public class DefaultLightSensor extends Sensor implements midgard.sensors.ILightSensor, ILightSensorThresholdListener {

    private com.sun.spot.sensorboard.peripheral.ILightSensor lightSensor;

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
        // ignore
    }

    public void initSensor(){
        lightSensor = EDemoBoard.getInstance().getLightSensor();
        lightSensor.addILightSensorThresholdListener(this); // register us as a listener
        lightSensor.setThresholds(10, 700);   //TODO: user conf thresholds
        lightSensor.enableThresholdEvents(true);            // turn on notification
    }

    public void disableSensor(){
        lightSensor.removeILightSensorThresholdListener(this);
        lightSensor.enableThresholdEvents(false);
        lightSensor = null;
    }

}
