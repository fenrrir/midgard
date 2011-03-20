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

import midgard.adaptation.IAdaptationManager;
import midgard.battery.IBatteryManager;
import midgard.componentmodel.Component;
import midgard.componentmodel.IComponent;
import midgard.components.IComponentManager;
import midgard.events.IEvent;
import midgard.events.IEventManager;
import midgard.kernel.MicroKernel;
import midgard.network.INetworkManager;
import midgard.network.routing.IRoutingManager;
import midgard.sensors.events.AccelerometerEvent;
import midgard.sensors.events.BatteryEvent;
import midgard.sensors.events.LightEvent;
import midgard.sensors.events.NetworkEvent;
import midgard.sensors.events.RoutingEvent;
import midgard.sensors.events.TemperatureEvent;
import midgard.tasks.ITaskManager;

/**
 *
 * @author fenrrir
 */
public class App extends Component implements IApp {

    private IComponent resolveName(String name){
        return MicroKernel.getInstance().getNameService().resolveName(name);
    }

    public IAdaptationManager getAdaptationManager() {
        return (IAdaptationManager) resolveName(IAdaptationManager.class.getName());
    }

    public IBatteryManager getBatteryManager() {
        return (IBatteryManager) resolveName(IBatteryManager.class.getName());
    }

    public IComponentManager getComponentManager() {
        return (IComponentManager) resolveName(IComponentManager.class.getName());
    }

    public IEventManager getEventManager() {
        return (IEventManager) resolveName(IEventManager.class.getName());
    }

    public INetworkManager getNetworkManager() {
        return (INetworkManager) resolveName(INetworkManager.class.getName());
    }

    public IRoutingManager getRoutingManager() {
        return (IRoutingManager) resolveName(IRoutingManager.class.getName());
    }

    public ITaskManager getTaskManager() {
        return (ITaskManager) resolveName(ITaskManager.class.getName());
    }

    public void handleAccelerometerEvent(AccelerometerEvent event){
        throw new NotImplementedListener();
    }

    public void handleBatteryEvent(BatteryEvent event){
        throw new NotImplementedListener();
    }

    public void handleLightEvent(LightEvent event){
        throw new NotImplementedListener();
    }

    public void handleNetworkEvent(NetworkEvent event){
        throw new NotImplementedListener();
    }

    public void handleRoutingEvent(RoutingEvent event){
        throw new NotImplementedListener();
    }

    public void handleTemperatureEvent(TemperatureEvent event){
        throw new NotImplementedListener();
    }

    public void newEventArrived(IEvent event)  {
        try {
            super.newEventArrived(event);
            if (event instanceof TemperatureEvent) {
                handleTemperatureEvent((TemperatureEvent) event);
            } else if (event instanceof RoutingEvent) {
                handleRoutingEvent((RoutingEvent) event);
            } else if (event instanceof NetworkEvent) {
                handleNetworkEvent((NetworkEvent) event);
            } else if (event instanceof LightEvent) {
                handleLightEvent((LightEvent) event);
            } else if (event instanceof BatteryEvent) {
                handleBatteryEvent((BatteryEvent) event);
            } else if (event instanceof AccelerometerEvent) {
                handleAccelerometerEvent((AccelerometerEvent) event);
            }
        } catch (NotImplementedListener ex) {
            ex.printStackTrace();
        }
    }
}
