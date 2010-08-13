/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.battery;
import midgard.services.Service;

/**
 *
 * @author fenrrir
 */
public class DefaultBatteryManager extends Service implements IBatteryManager {

    public DefaultBatteryManager() {
    }

   

    public double getAvailableCapacity() {
        return 0.0;
    }

    public int getBatteryLevel() {
        return 0;
    }

    public long getTime() {
        return 0;
    }

    

}
