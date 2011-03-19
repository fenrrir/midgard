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
