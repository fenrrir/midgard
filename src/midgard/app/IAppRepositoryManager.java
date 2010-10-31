/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.app;

import java.util.Vector;
import midgard.repositories.IRepositoryManager;
import midgard.sensors.light.ThresholdChangedLightData;

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
    public Vector listSensors();
    public long getSleepTime();
    public ThresholdChangedLightData getLightThreshold();
    
}
