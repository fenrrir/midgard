/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors.battery;

import midgard.battery.IBatteryManager;
import midgard.sensors.ISensor;

/**
 *
 * @author fenrrir
 */
public interface IBatterySensor extends ISensor{
    public IBatteryManager getManager();

}
