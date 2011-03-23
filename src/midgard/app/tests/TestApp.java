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

package midgard.app.tests;

import midgard.app.App;
import midgard.events.IEvent;
import midgard.kernel.Debug;
import midgard.sensors.accelerometer.AccelerometerSensorData;
import midgard.sensors.battery.BatterySensorData;
import midgard.sensors.events.AccelerometerEvent;
import midgard.sensors.events.BatteryEvent;
import midgard.sensors.events.LightEvent;
import midgard.sensors.events.NetworkEvent;
import midgard.sensors.events.TemperatureEvent;
import midgard.sensors.temperature.TemperatureSensorData;

/**
 *
 * @author fenrrir
 */
public class TestApp extends App{


    public void handleLightEvent(LightEvent event) {
        Debug.debug("light " + event.getContentObject().toString());
    }

    public void handleTemperatureEvent(TemperatureEvent event) {
        TemperatureSensorData data = (TemperatureSensorData) event.getContentObject();
        Debug.debug("temperature " + data.getCelsius());
    }

    public void handleNetworkEvent(NetworkEvent event) {
    }

    public void handleBatteryEvent(BatteryEvent event) {
        BatterySensorData data = (BatterySensorData) event.getContentObject();
        Debug.debug("battery" + data.getAvailableCapacity());
    }

    public void handleAccelerometerEvent(AccelerometerEvent event) {
        AccelerometerSensorData data = (AccelerometerSensorData) event.getContentObject();
        Debug.debug("accelerometer " + data.getAccel());

    }

    public void newEventArrived(IEvent event) {
        Debug.debug("Event arrived " + event.toString());
        super.newEventArrived(event);
    }








}
