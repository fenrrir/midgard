/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
