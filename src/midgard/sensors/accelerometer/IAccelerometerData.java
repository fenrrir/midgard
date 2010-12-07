/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors.accelerometer;

/**
 *
 * @author fenrrir
 */
public interface IAccelerometerData {

    public double getAccel();
    public double getAccel(int axis);
    public double getAccelX();
    public double getAccelY();
    public double getAccelZ();

    public double getRelativeAccel();
    public double getRelativeAccel(int axis);
    public double getRelativeAccelX();
    public double getRelativeAccelY();
    public double getRelativeAccelZ();

    public double getTilt(int axis);
    public double getTiltX();
    public double getTiltY();
    public double getTiltZ();

}
