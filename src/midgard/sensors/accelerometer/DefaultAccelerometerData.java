/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors.accelerometer;

import com.sun.spot.sensorboard.peripheral.IAccelerometer3D;
import java.io.IOException;

/**
 *
 * @author fenrrir
 */
public class DefaultAccelerometerData implements IAccelerometerData{

    private IAccelerometer3D accel;

    public DefaultAccelerometerData(IAccelerometer3D accel) {
        this.accel = accel;
    }

    public double getAccel() {
        try {
            return accel.getAccel();
        } catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public double getAccel(int axis) {
        try {
            return accel.getAccel(axis);
        } catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public double getAccelX() {
        try {
            return accel.getAccelX();
        } catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public double getAccelY() {
        try {
            return accel.getAccelY();
        } catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public double getAccelZ() {
        try {
            return accel.getAccelZ();
        } catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public double getRelativeAccel() {
        try {
            return accel.getRelativeAccel();
        } catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public double getRelativeAccel(int axis) {
        try {
            return accel.getRelativeAccel(axis);
        } catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public double getRelativeAccelX() {
        try {
            return accel.getRelativeAccelX();
        } catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public double getRelativeAccelY() {
        try {
            return accel.getRelativeAccelY();
        } catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public double getRelativeAccelZ() {
        try {
            return accel.getRelativeAccelZ();
        } catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public double getTilt(int axis) {
        try {
            return accel.getTilt(axis);
        } catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public double getTiltX() {
        try {
            return accel.getTiltX();
        } catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public double getTiltY() {
        try {
            return accel.getTiltY();
        } catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public double getTiltZ() {
        try {
            return accel.getTiltZ();
        } catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    

}
