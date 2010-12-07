/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.app;

import java.util.Vector;
import midgard.repositories.IRepositoryManager;
import midgard.sensors.accelerometer.AccelerometerThresholds;
import midgard.sensors.light.ThresholdChangedLightData;
import midgard.sensors.temperature.ThresholdChangedTemperatureData;

/**
 *
 * @author fenrrir
 */
public interface IAppRepositoryManager extends IRepositoryManager{
    public Vector getInstalledAppNames();
    public IApp getApp(String name);
    public IThreadedApp getThreadedApp(String name);
    public IApp getAppFromLabel(String name);
    public Vector listAppLabels();
    public Vector getSensorsForAppLabel(String name);
    public Vector listServices();
    public Vector listTasks();
    public Vector listSensors();
    public Vector listCustomEvents();
    public long getSleepTime();
    public ThresholdChangedLightData getLightThreshold();
    public ThresholdChangedTemperatureData getTemperatureThreshold();
    public AccelerometerThresholds getAccelerometerThreshold();
    
}
