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
import midgard.battery.IBatteryManager;
import midgard.sensors.ISensor;

/**
 *
 * @author fenrrir
 */
public interface IBatterySensor extends ISensor{

    public final byte CHARGING = IBattery.CHARGING;
    public final byte DEAD_BATTERY = IBattery.DEAD_BATTERY;
    public final byte DISCHARGING = IBattery.DISCHARGING;
    public final byte EXT_POWERED = IBattery.EXT_POWERED;
    public final byte LOW_BATTERY = IBattery.LOW_BATTERY;
    public final byte NO_BATTERY = IBattery.NO_BATTERY;
    public IBatteryManager getManager();

}
