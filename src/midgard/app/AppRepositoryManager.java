/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.app;

import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.repositories.RepositoryManager;
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





    public Vector listAppLabels() {
        return concreteComponent.listAppLabels();
    }

    public Vector getSensorsForAppLabel(String name) {
        return concreteComponent.getSensorsForAppLabel(name);
    }

    public IApp getAppFromLabel(String name) {
        return concreteComponent.getAppFromLabel(name);
    }




    


        
}
