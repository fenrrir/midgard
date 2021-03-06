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

/**
 *
 * @author fenrrir
 */
public class BatterySensorData implements IBatteryData{
    private IBattery battery;

    public BatterySensorData(IBattery battery) {
        this.battery = battery;
    }

    public byte getState() {
        return battery.getState();
    }

    public double getMaximumCapacity() {
        return battery.getMaximumCapacity();
    }

    public int getBatteryLevel() {
        return battery.getBatteryLevel();
    }

    public double getAvailableCapacity() {
        return battery.getAvailableCapacity();
    }

    public String getStateAsString() {
        byte state = getState();
        return battery.getStateAsString(state);
    }

}
