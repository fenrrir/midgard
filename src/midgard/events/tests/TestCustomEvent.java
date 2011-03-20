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

package midgard.events.tests;

import java.util.Vector;
import midgard.events.BaseCustomEvent;
import midgard.events.IEvent;
import midgard.sensors.events.LightEvent;
import midgard.sensors.events.TemperatureEvent;
import midgard.sensors.light.ILightSensor;
import midgard.sensors.temperature.ITemperatureSensor;
import midgard.sensors.temperature.TemperatureSensorData;

/**
 *
 * @author fenrrir
 */
public class TestCustomEvent extends BaseCustomEvent {
    private Object content = new String("Hello");
    private double temperature;
    private Integer light = null;
    private boolean lightUpdated = false;
    private boolean temperatureUpdated = false;



    public Vector connectIn() {
        Vector v = new Vector();
        v.addElement(ILightSensor.class.getName());
        v.addElement(ITemperatureSensor.class.getName());
        return v;
    }

    public Object getContentObject() {
        return content;
    }

    public void setContentObject(Object content) {
        this.content = content;
    }

    public void newEventArrived(IEvent event) {

        System.err.println("TestCustomEvent received event");

        if (event instanceof LightEvent){
            light = (Integer) event.getContentObject();
            lightUpdated = true;
        }
        if (event instanceof TemperatureEvent){
            TemperatureSensorData data = (TemperatureSensorData) event.getContentObject();
            temperature = data.getCelsius();
            temperatureUpdated = true;
        }

        if (lightUpdated && temperatureUpdated){
            if (light.intValue() < 50 && temperature < 10){
                fireEvent();
            }
        }
    }

    public void fireEvent(IEvent event) {
        System.err.println("fireEvent on TestCustomEvent: " + event.getContentObject().toString());
        super.fireEvent(event);
    }





}
