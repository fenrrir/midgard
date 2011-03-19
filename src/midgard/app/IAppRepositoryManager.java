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
    public Vector listAdaptationProfiles();
    public Vector listWebApplications();
    public long getSleepTime();
    public ThresholdChangedLightData getLightThreshold();
    public ThresholdChangedTemperatureData getTemperatureThreshold();
    public AccelerometerThresholds getAccelerometerThreshold();
    
}
