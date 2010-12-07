/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
