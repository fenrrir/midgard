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

package midgard.sensors.accelerometer;

import com.sun.spot.sensorboard.peripheral.IAccelerometer3D;
import midgard.sensors.ISensor;

/**
 *
 * @author fenrrir
 */
public interface IAccelerometerSensor extends ISensor{
    public final int X_AXIS = IAccelerometer3D.X_AXIS;
    public final int Y_AXIS = IAccelerometer3D.Y_AXIS;
    public final int Z_AXIS = IAccelerometer3D.Z_AXIS;
    public final int ALL_AXIS = IAccelerometer3D.ALL_AXES;
    public void enableThresholds( AccelerometerThresholds thresholds );
    public void disableThresholds();
}
