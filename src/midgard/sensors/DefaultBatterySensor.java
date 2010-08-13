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
public class DefaultBatterySensor extends Sensor implements IBatterySensor{

    public IBatteryManager getManager(){
        //TODO preciso usar resolvedor de nomes aqui
        return null;
    }

}
