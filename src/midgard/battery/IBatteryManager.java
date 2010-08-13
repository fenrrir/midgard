/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.battery;

import midgard.services.IService;

/**
 *
 * @author fenrrir
 */
public interface IBatteryManager extends IService {
    double getAvailableCapacity();
    int getBatteryLevel();
    long getTime();

}
