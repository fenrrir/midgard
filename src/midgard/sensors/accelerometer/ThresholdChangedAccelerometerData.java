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

/**
 *
 * @author fenrrir
 */
public class ThresholdChangedAccelerometerData {

    public int axis;
    public boolean relative;
    public double low, high;

    public ThresholdChangedAccelerometerData(int axis, boolean relative, double low, double high) {
        this.axis = axis;
        this.relative = relative;
        this.low = low;
        this.high = high;
    }

    public int getAxis() {
        return axis;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public boolean isRelative() {
        return relative;
    }

    

}
