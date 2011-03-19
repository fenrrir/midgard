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
import midgard.componentmodel.IComponent;
import midgard.repositories.RepositoryManager;
import midgard.sensors.accelerometer.AccelerometerThresholds;
import midgard.sensors.light.ThresholdChangedLightData;
import midgard.sensors.temperature.ThresholdChangedTemperatureData;

/**
 *
 * @author fenrrir
 */
public class AppRepositoryManager extends RepositoryManager implements IAppRepositoryManager{
    private IAppRepositoryManager concreteComponent;
    
    
    public void setConcreteComponent(IComponent concreteComponent){
        super.setConcreteComponent(concreteComponent);
        this.concreteComponent = (IAppRepositoryManager) concreteComponent;
    }

    public IThreadedApp getThreadedApp(String name) {
        return concreteComponent.getThreadedApp(name);
    }

    public Vector getInstalledAppNames() {
        return concreteComponent.getInstalledAppNames();
    }

    public IApp getApp(String name) {
        return concreteComponent.getApp(name);
    }

    public Vector listServices() {
        return concreteComponent.listServices();
    }

    public Vector listSensors() {
        return concreteComponent.listSensors();
    }

    public long getSleepTime() {
        return concreteComponent.getSleepTime();
    }

    public ThresholdChangedLightData getLightThreshold() {
        return concreteComponent.getLightThreshold();
    }

    public ThresholdChangedTemperatureData getTemperatureThreshold() {
        return concreteComponent.getTemperatureThreshold();
    }

    public AccelerometerThresholds getAccelerometerThreshold() {
        return concreteComponent.getAccelerometerThreshold();
    }

    
    public Vector listTasks() {
        return concreteComponent.listTasks();
    }

    public Vector listCustomEvents() {
        return concreteComponent.listCustomEvents();
    }

    
    
    public Vector listAppLabels() {
        return concreteComponent.listAppLabels();
    }

    public Vector getSensorsForAppLabel(String name) {
        return concreteComponent.getSensorsForAppLabel(name);
    }

    public IApp getAppFromLabel(String name) {
        return concreteComponent.getAppFromLabel(name);
    }

    public Vector listAdaptationProfiles() {
        return concreteComponent.listAdaptationProfiles();
    }

    public Vector listWebApplications() {
        return concreteComponent.listWebApplications();
    }

    
}
