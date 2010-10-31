/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors;

import com.sun.spot.util.Utils;
import java.util.Vector;
import midgard.app.IApp;
import midgard.app.IAppRepositoryManager;
import midgard.componentmodel.IComponent;
import midgard.components.IComponentManager;
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
    private Thread thread = null;

    public String[] getRequiredInterfaces() {
        return new String [] {IAppRepositoryManager.class.getName(),
                              IComponentManager.class.getName()} ;
    }

    public void connect(String interfaceName, IComponent component) {
        System.err.println("Conectando " + interfaceName);
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

    public void run() {
        while (true){
            collect();
            Utils.sleep(appRepositoryManager.getSleepTime());
            System.err.println("sensormanager tick");
        }
    }

    public void collect() {
        ISensor sensor;
        for (int i=0; i<userSensors.size(); i++){
            sensor = (ISensor )userSensors.elementAt(i);
            sensor.collect();
        }
    }

    public void disableSensor() {
        ISensor sensor;
        for (int i=0; i<userSensors.size(); i++){
            sensor = (ISensor )userSensors.elementAt(i);
            sensor.disableSensor();
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

                System.err.println("ComponentManager " + componentManager);

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

        if (thread == null){
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stopService() {
        super.stopService();
        thread.interrupt();
    }

    public void destroy() {
        super.destroy();
        disableSensor();
    }

    public void initialize() {
        super.initialize();
        initSensor();
    }

    public void pause() {
        super.pause();
        disableSensor();
    }

    public void resume() {
        super.resume();
        initSensor();
    }






}
