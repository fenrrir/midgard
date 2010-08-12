/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors;

import midgard.battery.IBatteryManager;
import midgard.events.IEvent;

/**
 *
 * @author fenrrir
 */
public class BatterySensor extends Sensor{

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


    public IBatteryManager getManager(){
        //TODO preciso usar resolvedor de nomes aqui
        return null;
    }

}
