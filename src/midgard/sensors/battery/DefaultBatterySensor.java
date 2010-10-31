/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors.battery;

import midgard.battery.IBatteryManager;
import midgard.kernel.MicroKernel;
import midgard.naming.INameService;
import midgard.sensors.Sensor;

/**
 *
 * @author fenrrir
 */
public class DefaultBatterySensor extends Sensor implements IBatterySensor{

    public IBatteryManager getManager(){
        INameService naming = MicroKernel.getInstance().getNameService();
        return (IBatteryManager) naming.resolveName("BatteryManager");
    }

    public void collect() {
    }

    public void disableSensor() {
    }

    public void initSensor() {
    }



}
