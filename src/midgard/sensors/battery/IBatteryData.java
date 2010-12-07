/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors.battery;

import midgard.componentmodel.IComponent;

/**
 *
 * @author fenrrir
 */
public interface IBatteryData{

    public double getAvailableCapacity();
    public int getBatteryLevel();
    public double getMaximumCapacity();
    public byte getState();
    public String getStateAsString();

}
