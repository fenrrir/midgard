/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors.battery;

import com.sun.spot.peripheral.IBattery;
import midgard.battery.IBatteryManager;
import midgard.sensors.ISensor;

/**
 *
 * @author fenrrir
 */
public interface IBatterySensor extends ISensor{

    public final byte CHARGING = IBattery.CHARGING;
    public final byte DEAD_BATTERY = IBattery.DEAD_BATTERY;
    public final byte DISCHARGING = IBattery.DISCHARGING;
    public final byte EXT_POWERED = IBattery.EXT_POWERED;
    public final byte LOW_BATTERY = IBattery.LOW_BATTERY;
    public final byte NO_BATTERY = IBattery.NO_BATTERY;
    public IBatteryManager getManager();

}
