/*
* Copyright (C) 2011 Rodrigo Pinheiro Marques de Araujo
*
* This program is free software; you can redistribute it and/or modify it under
* the terms of the GNU General Public License as published by the Free Software
* Foundation; either version 2 of the License, or (at your option) any later
* version.
*
* This program is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
* FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
* details.
*
* You should have received a copy of the GNU General Public License along with
* this program; if not, write to the Free Software Foundation, Inc., 51
* Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
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
            //System.err.println("Temperature device an instance of " + tempSensor.getClass() + ", with range = " + range);
            int temp = tempSensor.getValue(); // The raw reading
            double tempF = tempSensor.getFahrenheit(); // The value converted to Farenheight
            double tempC = tempSensor.getCelsius(); // The value converted to Celcius.
            //System.err.println("tempSensor.getValue() = " + temp + " (raw value) = " + tempF + " degrees F = " + tempC + " degrees C.");
            Object content = new TemperatureSensorData(tempC, tempF);
            fireEvent(new TemperatureEvent(content));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void connect(String interfaceName, IComponent component) {
        super.connect(interfaceName, component);
        if (interfaceName.equals(IAppRepositoryManager.class.getName())) {
            appRepositoryManager = (IAppRepositoryManager) component;
        }
    }

    public String[] getRequiredInterfaces() {
        return new String[]{IAppRepositoryManager.class.getName()};
    }

    public void thresholdChanged(ITemperatureInput temp, double low, double high, boolean inCelsius) {
        //System.err.println("Temperature threshold changed"); //ignore this
    }

    public void thresholdExceeded(ITemperatureInput temp, double val, boolean inCelsius) {
        //System.err.println("Temperature threshold exceeded val=" + val + " celsius?" + inCelsius);
        fireEvent(new ThresholdExceededTemperatureEvent(new Double(val)));
    }

    public void initSensor() {
        double high, low;
        ThresholdChangedTemperatureData thresholdChangedTemperatureData;

        tempSensor = EDemoBoard.getInstance().getADCTemperature();

        thresholdChangedTemperatureData = appRepositoryManager.getTemperatureThreshold();

        if (thresholdChangedTemperatureData != null) {
            high = thresholdChangedTemperatureData.high;
            low = thresholdChangedTemperatureData.low;
            enableThresholds(low, high, true);
        } else {
            //System.err.println("Temperature threshold not seted");
        }
    }

    public void disableSensor() {
        disableThresholds();
        tempSensor = null;

    }

    public void disableThresholds() {
        tempSensor.removeITemperatureInputThresholdListener(this);
        tempSensor.enableThresholdEvents(false);
    }

    public void enableThresholds(double low, double high, boolean celsius) {
        tempSensor.addITemperatureInputThresholdListener(this);
        tempSensor.setThresholds(low, high, celsius);
        tempSensor.enableThresholdEvents(true);

    }
}
