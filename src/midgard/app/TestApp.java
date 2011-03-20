/*
* Copyright (C) 2011 Rodrigo Pinheiro Marques de Araujo
*
* This program is free software; you can redistribute it and/or modify it under
* the terms of the GNU General Public License as published by the Free Software
* Foundation; either version 2 of the License, or (at your option) any later
* version.
*
* This program is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
* FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
* details.
*
* You should have received a copy of the GNU General Public License along with
* this program; if not, write to the Free Software Foundation, Inc., 51
* Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
*/

package midgard.app;

import midgard.events.IEvent;
import midgard.sensors.battery.IBatteryData;
import midgard.sensors.events.AccelerometerEvent;
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

    public void handleAccelerometerEvent(AccelerometerEvent event) {
        
    }



    public void newEventArrived(IEvent event) {
        System.err.println("Event arrived " + event.toString());
        super.newEventArrived(event);
    }








}
