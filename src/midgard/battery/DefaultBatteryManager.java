/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.battery;
import com.sun.spot.peripheral.IBattery;
import com.sun.spot.peripheral.Spot;
import midgard.services.Service;

/**
 *
 * @author fenrrir
 */
public class DefaultBatteryManager extends Service implements IBatteryManager {
    private IBattery battery;

    public void initialize() {
        super.initialize();
        battery = Spot.getInstance().getPowerController().getBattery();
    }

    public void destroy() {
        super.destroy();
        battery = null;
    }


    public double getAvailableCapacity() {
        return battery.getAvailableCapacity();
    }

    public int getBatteryLevel() {
        return battery.getBatteryLevel();
    }

    public long getTime() {
        long [] times = battery.getTime();
        return times[times.length - 1];
    }

    

}
