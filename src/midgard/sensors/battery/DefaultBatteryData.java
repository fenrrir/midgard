/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors.battery;

import com.sun.spot.peripheral.IBattery;

/**
 *
 * @author fenrrir
 */
public class DefaultBatteryData implements IBatteryData{
    private IBattery battery;

    public DefaultBatteryData(IBattery battery) {
        this.battery = battery;
    }

    public byte getState() {
        return battery.getState();
    }

    public double getMaximumCapacity() {
        return battery.getMaximumCapacity();
    }

    public int getBatteryLevel() {
        return battery.getBatteryLevel();
    }

    public double getAvailableCapacity() {
        return battery.getAvailableCapacity();
    }

    public String getStateAsString() {
        byte state = getState();
        return battery.getStateAsString(state);
    }

}
