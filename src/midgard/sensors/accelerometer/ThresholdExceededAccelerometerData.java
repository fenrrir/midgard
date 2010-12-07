/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors.accelerometer;

/**
 *
 * @author fenrrir
 */
public class ThresholdExceededAccelerometerData {

    public int axis;
    public boolean relative;
    public double value;

    public ThresholdExceededAccelerometerData(int axis, boolean relative, double value) {
        this.axis = axis;
        this.relative = relative;
        this.value = value;
    }

    public int getAxis() {
        return axis;
    }

    public boolean isRelative() {
        return relative;
    }

    public double getValue() {
        return value;
    }

    

}
