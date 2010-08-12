/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.battery;

import midgard.componentmodel.IComponent;

/**
 *
 * @author fenrrir
 */
public interface IBatteryManager extends IComponent {
    double getAvailableCapacity();
    int getBatteryLevel();
    long getTime();

}
