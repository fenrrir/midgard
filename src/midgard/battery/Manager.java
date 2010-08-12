/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.battery;
import midgard.battery.IBatteryManager;
import midgard.componentmodel.Component;
import midgard.events.IEvent;

/**
 *
 * @author fenrrir
 */
public class Manager extends Component implements IBatteryManager {

    public Manager() {
    }

    public void destroy() {
    }

    public void initialize() {
    }

    public void load() {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void newEventArrived(IEvent event) {
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
