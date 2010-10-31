/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.app;

import midgard.battery.IBatteryManager;
import midgard.componentmodel.IComponent;
import midgard.components.IComponentManager;
import midgard.network.INetworkManager;
import midgard.network.routing.IRoutingManager;
import midgard.sensors.events.AccelerometerEvent;
import midgard.sensors.events.BatteryEvent;
import midgard.sensors.events.LightEvent;
import midgard.sensors.events.NetworkEvent;
import midgard.sensors.events.RoutingEvent;
import midgard.sensors.events.TemperatureEvent;

/**
 *
 * @author fenrrir
 */
public interface IApp extends IComponent  {
    IComponent getTaskManager();
    IComponentManager getComponentManager();
    IComponent getEventManager();
    IComponent getAdapterManager();
    IBatteryManager getBatteryManager();
    INetworkManager getNetworkManager();
    IRoutingManager getRoutingManager();
    void handleBatteryEvent(BatteryEvent event) throws NotImplementedListener;
    void handleNetworkEvent(NetworkEvent event) throws NotImplementedListener;
    void handleLightEvent(LightEvent event) throws NotImplementedListener;
    void handleTemperatureEvent(TemperatureEvent event) throws NotImplementedListener;
    void handleAccelerometerEvent(AccelerometerEvent event) throws NotImplementedListener;
    void handleRoutingEvent(RoutingEvent event) throws NotImplementedListener;

}
