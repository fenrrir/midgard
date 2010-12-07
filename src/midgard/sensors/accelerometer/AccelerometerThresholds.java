/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors.accelerometer;

/**
 *
 * @author fenrrir
 */
public class AccelerometerThresholds {

    public double xLow, xHigh, yLow, yHigh, zLow, zHigh;
    public boolean relative;

    public AccelerometerThresholds(double xLow, double xHigh,
                                   double yLow, double yHigh,
                                   double zLow, double zHigh,
                                   boolean relative) {
        this.xLow = xLow;
        this.xHigh = xHigh;
        this.yLow = yLow;
        this.yHigh = yHigh;
        this.zLow = zLow;
        this.zHigh = zHigh;
        this.relative = relative;
    }

    public double getXLow() {
        return xLow;
    }

    public double getXHigh() {
        return xHigh;
    }

    public double getYLow() {
        return yLow;
    }

    public double getYHigh() {
        return yHigh;
    }

    public double getZLow() {
        return zLow;
    }

    public double getZHigh() {
        return zHigh;
    }





}
