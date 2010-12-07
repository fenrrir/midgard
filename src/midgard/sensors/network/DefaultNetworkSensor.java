/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
        super.newEventArrived(event);
        fireEvent(event);
    }

    public void disableSensor() {
        webserver.stopService();
    }

    public void initSensor() {
        webserver.registerEventListener(this);
        webserver.startService();
    }
   
}
