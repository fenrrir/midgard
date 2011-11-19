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

package midgard.sensors;

import com.sun.spot.util.Utils;
import java.util.Vector;
import midgard.app.IApp;
import midgard.app.IAppRepositoryManager;
import midgard.componentmodel.IComponent;
import midgard.components.IComponentManager;
import midgard.kernel.Debug;
import midgard.kernel.MicroKernel;
import midgard.naming.INameService;
import midgard.sensors.accelerometer.IAccelerometerSensor;
import midgard.sensors.battery.IBatterySensor;
import midgard.sensors.light.ILightSensor;
import midgard.sensors.network.INetworkSensor;
import midgard.sensors.temperature.ITemperatureSensor;
import midgard.services.Service;

/**
 *
 * @author fenrrir
 */
public class DefaultSensorManager extends Service implements ISensorManager, ISensor {
    private Vector userSensors;
    private IAppRepositoryManager appRepositoryManager;
    private IComponentManager componentManager;
    protected boolean isRunning;

    public String[] getRequiredInterfaces() {
        return new String [] {IAppRepositoryManager.class.getName(),
                              IComponentManager.class.getName()} ;
    }

    public void connect(String interfaceName, IComponent component) {
        //System.err.println("Conectando " + interfaceName);
        super.connect(interfaceName, component);
        if (interfaceName.equals(IAppRepositoryManager.class.getName())){
            appRepositoryManager = (IAppRepositoryManager) component;
        }else if (interfaceName.equals(IComponentManager.class.getName())){
            componentManager = (IComponentManager) component;
        }
    }

    public IAccelerometerSensor getAccelerometerSensor() {
        INameService naming = MicroKernel.getInstance().getNameService();
        return (IAccelerometerSensor) naming.resolveName(IAccelerometerSensor.class.getName());
    }

    public IBatterySensor getBatterySensor() {
        INameService naming = MicroKernel.getInstance().getNameService();
        return (IBatterySensor) naming.resolveName(IBatterySensor.class.getName());
    }

    public ILightSensor getLight() {
        INameService naming = MicroKernel.getInstance().getNameService();
        return (ILightSensor) naming.resolveName(ILightSensor.class.getName());
    }

    public INetworkSensor getNetworkSensor() {
        INameService naming = MicroKernel.getInstance().getNameService();
        return (INetworkSensor) naming.resolveName(INetworkSensor.class.getName());
    }

    public ITemperatureSensor getThemperatureSensor() {
        INameService naming = MicroKernel.getInstance().getNameService();
        return (ITemperatureSensor) naming.resolveName(ITemperatureSensor.class.getName());
    }

    
    public void collect() {
        ISensor sensor;
        for (int i=0; i<userSensors.size(); i++){
            sensor = (ISensor )userSensors.elementAt(i);
            sensor.collect();
        }
        Debug.showMemoryStats("after life cycle");
    }

    public void disableSensor() {
        ISensor sensor;
        for (int i=0; i<userSensors.size(); i++){
            sensor = (ISensor )userSensors.elementAt(i);
            //sensor.disableSensor();
        }

        userSensors.removeAllElements();
        userSensors = null;
    }

    public void initSensor() {
        String label, sensorName;
        IApp app;
        ISensor sensor;
        Vector appSensors;
        
        userSensors = new Vector();
        
        Vector appLabels = appRepositoryManager.listAppLabels();

        for (int i=0; i < appLabels.size(); i++){
            label = (String) appLabels.elementAt(i);
            app = appRepositoryManager.getAppFromLabel(label);

            appSensors = appRepositoryManager.getSensorsForAppLabel(label);

            for (int j=0; j < appSensors.size(); j++){
                sensorName = (String) appSensors.elementAt(j);

                //System.err.println("ComponentManager " + componentManager);

                sensor = (ISensor) componentManager.resolveComponent(sensorName);
                sensor.registerEventListener(app);

                if (!userSensors.contains(sensor)){
                    userSensors.addElement(sensor);
                }

            }
        }
    }

    public void startService() {
        super.startService();
        isRunning = true;
        while (isRunning){
            collect();
            Utils.sleep(appRepositoryManager.getSleepTime());
            //System.err.println("sensormanager tick");
        }
    }

    

    public void stopService() {
        isRunning = false;
        super.stopService();
        
    }

    public void destroy() {

        if (!isPaused()){
            stopService();
        }
        disableSensor();
        super.destroy();
    }

    public void initialize() {
        super.initialize();
        initSensor();
    }

    public void pause() {
        super.pause();
        stopService();
    }

    public void resume() {
        super.resume();
        startService();
    }






}
