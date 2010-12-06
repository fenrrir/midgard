/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midgard.sensors.temperature;

import com.sun.spot.sensorboard.EDemoBoard;
import com.sun.spot.sensorboard.peripheral.ITemperatureInput;
import com.sun.spot.sensorboard.peripheral.ITemperatureInputThresholdListener;
import java.io.IOException;
import midgard.app.IAppRepositoryManager;
import midgard.componentmodel.IComponent;
import midgard.sensors.Sensor;
import midgard.sensors.events.TemperatureEvent;
import midgard.sensors.events.ThresholdExceededTemperatureEvent;

/**
 *
 * @author fenrrir
 */
public class DefaultTemperatureSensor extends Sensor implements ITemperatureSensor, ITemperatureInputThresholdListener {
    private ITemperatureInput tempSensor;
    private IAppRepositoryManager appRepositoryManager;

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

    public void connect(String interfaceName, IComponent component) {
        super.connect(interfaceName, component);
        if (interfaceName.equals(IAppRepositoryManager.class.getName())){
            appRepositoryManager = (IAppRepositoryManager) component;
        }
    }

    public String[] getRequiredInterfaces() {
        return new String [] {IAppRepositoryManager.class.getName()};
    }

    public void thresholdChanged(ITemperatureInput temp, double low, double high, boolean inCelsius) {
        System.err.println("Temperature threshold changed"); //ignore this
    }

    public void thresholdExceeded(ITemperatureInput temp, double val, boolean inCelsius) {
        System.err.println("Temperature threshold exceeded val=" + val + " celsius?" + inCelsius);
        fireEvent( new  ThresholdExceededTemperatureEvent(new Double(val)));
    }


    
    public void initSensor(){
        double high, low;
        ThresholdChangedTemperatureData thresholdChangedTemperatureData;

        tempSensor = EDemoBoard.getInstance().getADCTemperature();

        thresholdChangedTemperatureData = appRepositoryManager.getTemperatureThreshold();
        high = thresholdChangedTemperatureData.high;
        low = thresholdChangedTemperatureData.low;

        if (!( high == low && low == -1 )){
            tempSensor.addITemperatureInputThresholdListener(this);
            tempSensor.setThresholds(10, 30, true);
            tempSensor.enableThresholdEvents(true);
        }
        else{
            System.err.println("Temperature threshold not seted");
        }
    }
    
    public void disableSensor(){
        tempSensor.removeITemperatureInputThresholdListener(this);
        tempSensor.enableThresholdEvents(false);

    }

    





}
