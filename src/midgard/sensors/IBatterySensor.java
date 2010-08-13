/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors;

import midgard.battery.IBatteryManager;

/**
 *
 * @author fenrrir
 */
public interface IBatterySensor extends ISensor{
    public IBatteryManager getManager();

}
