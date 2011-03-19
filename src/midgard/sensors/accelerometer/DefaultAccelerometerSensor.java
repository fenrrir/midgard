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
