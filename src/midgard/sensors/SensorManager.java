/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors;

import midgard.kernel.MicroKernel;
import midgard.naming.INameService;
import midgard.services.Service;

/**
 *
 * @author fenrrir
 */
public class SensorManager extends Service{
    
   
    public static IAccelerometerSensor getAccelerometerSensor() {
        INameService naming = MicroKernel.getInstance().getNameService();
        return (IAccelerometerSensor) naming.resolveName("AccelerometerSensor");
    }

    public static IBatterySensor getBatterySensor() {
        INameService naming = MicroKernel.getInstance().getNameService();
        return (IBatterySensor) naming.resolveName("BatterySensor");
    }

    public static ILightSensor getLight() {
        INameService naming = MicroKernel.getInstance().getNameService();
        return (ILightSensor) naming.resolveName("LightSensor");
    }

    public static INetworkSensor getNetworkSensor() {
        INameService naming = MicroKernel.getInstance().getNameService();
        return (INetworkSensor) naming.resolveName("NetworkSensor");
    }

    public static IThemperatureSensor getThemperatureSensor() {
        INameService naming = MicroKernel.getInstance().getNameService();
        return (IThemperatureSensor) naming.resolveName("ThemperatureSensor");
    }

   
}
