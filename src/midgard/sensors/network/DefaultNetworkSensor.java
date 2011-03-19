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

package midgard.sensors.network;

import midgard.components.IComponentManager;
import midgard.events.IEvent;
import midgard.sensors.Sensor;
import midgard.web.IWebServer;




/**
 *
 * @author fenrrir
 */
public class DefaultNetworkSensor extends Sensor implements INetworkSensor{

    private IWebServer webserver;


    public String[] getRequiredInterfaces() {
        return new String [] {IWebServer.class.getName()}  ;
    }

    public void initialize(){
        webserver = (IWebServer)
                getConnectedComponents().get(IWebServer.class.getName());
        super.initialize();
    }

    public void collect() {
        // do nothing.
        // go to newEventArrived
              
    }

    public void newEventArrived(IEvent event) {
        //System.err.println("@@@@@@@@@@@@@@@@@@Event class " + event.getClass().getName());
        super.newEventArrived(event);
        fireEvent(event);
    }

    public void disableSensor() {
        webserver.removeEventListener(this);
        webserver.stopService();
    }

    public void initSensor() {
        webserver.registerEventListener(this);
        webserver.startService();
    }
   
}
