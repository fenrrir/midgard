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

package midgard.sensors.battery;

import com.sun.spot.peripheral.IBattery;
import com.sun.spot.peripheral.Spot;
import midgard.battery.IBatteryManager;
import midgard.sensors.Sensor;
import midgard.sensors.events.BatteryEvent;

/**
 *
 * @author fenrrir
 */
public class DefaultBatterySensor extends Sensor implements IBatterySensor{
    private IBatteryManager manager;
    private IBattery battery;

    public String[] getRequiredInterfaces() {
        return new String [] {
            IBatteryManager.class.getName()
        };
    }

    public void initialize(){
        manager =  (IBatteryManager)
                getConnectedComponents().get(IBatteryManager.class.getName());

        super.initialize();
    }



    public IBatteryManager getManager(){
        return manager;
    }

    public void collect() {
        IBatteryData data = new DefaultBatteryData(battery);
        fireEvent( new BatteryEvent( data ) );
    }

    public void disableSensor() {
        battery = null;
    }

    public void initSensor() {
        battery = Spot.getInstance().getPowerController().getBattery();
    }



}
