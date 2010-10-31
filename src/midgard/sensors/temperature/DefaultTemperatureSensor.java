/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midgard.sensors.temperature;

import midgard.sensors.temperature.ITemperatureSensor;
import midgard.sensors.temperature.TemperatureSensorData;
import com.sun.spot.sensorboard.EDemoBoard;
import com.sun.spot.sensorboard.peripheral.ITemperatureInput;
import com.sun.spot.sensorboard.peripheral.ITemperatureInputThresholdListener;
import java.io.IOException;
import midgard.sensors.Sensor;
import midgard.sensors.events.TemperatureEvent;
import midgard.sensors.events.ThresholdExceededTemperatureEvent;

/**
 *
 * @author fenrrir
 */
public class DefaultTemperatureSensor extends Sensor implements ITemperatureSensor, ITemperatureInputThresholdListener {
    private ITemperatureInput tempSensor;

    public void collect() {

        try {
            int range = tempSensor.getRange();
            System.err.println("Temperature device an instance of " + tempSensor.getClass() + ", with range = " + range);
            int temp = tempSensor.getValue(); // The raw reading
            double tempF = tempSensor.getFahrenheit(); // The value converted to Farenheight
            double tempC = tempSensor.getCelsius(); // The value converted to Celcius.
            System.err.println("tempSensor.getValue() = " + temp + " (raw value) = " + tempF + " degrees F = " + tempC + " degrees C.");
            Object content = new TemperatureSensorData(tempC, tempF);
            fireEvent(new TemperatureEvent(content));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    public void thresholdChanged(ITemperatureInput temp, double low, double high, boolean inCelsius) {
        System.err.println("Temperature threshold changed"); //ignore this
    }

    public void thresholdExceeded(ITemperatureInput temp, double val, boolean inCelsius) {
        System.err.println("Temperature threshold exceeded val=" + val + " celsius?" + inCelsius);
        fireEvent( new  ThresholdExceededTemperatureEvent(new Double(val)));
    }


    
    public void initSensor(){
        tempSensor = EDemoBoard.getInstance().getADCTemperature();
        tempSensor.addITemperatureInputThresholdListener(this);
        tempSensor.setThresholds(10, 30, true);
        tempSensor.enableThresholdEvents(true);
    }
    
    public void disableSensor(){
        tempSensor.removeITemperatureInputThresholdListener(this);
        tempSensor.enableThresholdEvents(false);

    }

    





}
