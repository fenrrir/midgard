/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.app;

import midgard.sensors.battery.IBatteryData;
import midgard.sensors.events.BatteryEvent;
import midgard.sensors.events.LightEvent;
import midgard.sensors.events.NetworkEvent;
import midgard.sensors.events.TemperatureEvent;

/**
 *
 * @author fenrrir
 */
public class TestApp extends App{


    public void destroy() {
        super.destroy();
        //System.err.println(getName() + " destroy");
    }

    public void initialize() {
        super.initialize();
        //System.err.println("@@@@@ " + getName() + " initialize");
        
    }

    public void load() {
        super.load();
        //System.err.println("@@@@@@ " + getName() + " load");
    }

    public void pause() {
        super.pause();
        //System.err.println(getName() + " pause");
    }

    public void resume() {
        super.resume();
        //System.err.println(getName() + " resume");
    }

    public void handleLightEvent(LightEvent event) {
        //System.err.println("Evento de luz recebido");
    }

    public void handleTemperatureEvent(TemperatureEvent event) {
        //System.err.println("Evento de temperatura recebido");
        
    }

    public void handleNetworkEvent(NetworkEvent event) {
        //System.err.println("Evento de rede recebido");
    }

    public void handleBatteryEvent(BatteryEvent event) {
        
    }




}
