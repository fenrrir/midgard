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
