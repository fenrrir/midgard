/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midgard.sensors.accelerometer;

import com.sun.spot.sensorboard.EDemoBoard;
import com.sun.spot.sensorboard.peripheral.IAccelerometer3D;
import com.sun.spot.sensorboard.peripheral.IAccelerometer3DThresholdListener;
import midgard.app.IAppRepositoryManager;
import midgard.sensors.Sensor;
import midgard.sensors.events.AccelerometerEvent;
import midgard.sensors.events.ThresholdChangedAccelerometerEvent;
import midgard.sensors.events.ThresholdExceededAccelerometerEvent;

/**
 *
 * @author fenrrir
 */
public class DefaultAccelerometerSensor extends Sensor
        implements IAccelerometerSensor, IAccelerometer3DThresholdListener {

    private IAccelerometer3D accel;
    private IAppRepositoryManager appRepositoryManager;

    public String[] getRequiredInterfaces() {
        return new String[]{IAppRepositoryManager.class.getName()};
    }

    public void initialize() {
        appRepositoryManager = (IAppRepositoryManager) getConnectedComponents().get(IAppRepositoryManager.class.getName());

        super.initialize();
    }

    public void collect() {
        IAccelerometerData data = new DefaultAccelerometerData(accel);
        fireEvent(new AccelerometerEvent(data));
    }

    public void disableSensor() {
        disableThresholds();
        accel = null;
    }

    public void initSensor() {
        accel = EDemoBoard.getInstance().getAccelerometer();
        AccelerometerThresholds thresholds = appRepositoryManager.getAccelerometerThreshold();

        if (thresholds != null) {
            enableThresholds(thresholds);
        } else {
            //System.err.println("Accelerometer threshold not seted");
        }

    }

    public void thresholdChanged(IAccelerometer3D accel,
            int axis, double low, double high, boolean relative) {
        fireEvent(new ThresholdChangedAccelerometerEvent(
                new ThresholdChangedAccelerometerData(axis, relative, low, high)));
    }

    public void thresholdExceeded(IAccelerometer3D accel,
            int axis, double val, boolean relative) {
        fireEvent(new ThresholdExceededAccelerometerEvent(
                new ThresholdExceededAccelerometerData(axis, relative, val)));

    }

    public void disableThresholds() {
        accel.removeIAccelerometer3DThresholdListener(this);
        accel.enableThresholdEvents(ALL_AXIS, false);
    }

    public void enableThresholds(AccelerometerThresholds thresholds) {
        accel.addIAccelerometer3DThresholdListener(this);
        accel.setThresholds(X_AXIS, thresholds.xLow, thresholds.xHigh, thresholds.relative);
        accel.setThresholds(Y_AXIS, thresholds.yLow, thresholds.yHigh, thresholds.relative);
        accel.setThresholds(Z_AXIS, thresholds.zLow, thresholds.zHigh, thresholds.relative);
        accel.enableThresholdEvents(ALL_AXIS, true);
    }
}
