/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.app;

import midgard.battery.IBatteryManager;
import midgard.componentmodel.Component;
import midgard.componentmodel.IComponent;
import midgard.components.IComponentManager;
import midgard.events.IEvent;
import midgard.kernel.MicroKernel;
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
public class App extends Component implements IApp {

    private IComponent resolveName(String name){
        return MicroKernel.getInstance().getNameService().resolveName(name);
    }

    public IComponent getAdapterManager() {
        return resolveName("midgard.adapter.IAdapterManager");
    }

    public IBatteryManager getBatteryManager() {
        return (IBatteryManager) resolveName("midgard.battery.IBatteryManager");
    }

    public IComponentManager getComponentManager() {
        return (IComponentManager) resolveName("midgard.components.IComponentManager");
    }

    public IComponent getEventManager() {
        return (IComponent) resolveName("midgard.events.IEventManager");
    }

    public INetworkManager getNetworkManager() {
        return (INetworkManager) resolveName("midgard.network.INetworkManager");
    }

    public IRoutingManager getRoutingManager() {
        return (IRoutingManager) resolveName("midgard.network.routing.IRoutingManager");
    }

    public IComponent getTaskManager() {
        return (IComponent) resolveName("midgard.tasks.ITaskManager");
    }

    public void handleAccelerometerEvent(AccelerometerEvent event) throws NotImplementedListener {
        throw new NotImplementedListener();
    }

    public void handleBatteryEvent(BatteryEvent event) throws NotImplementedListener {
        throw new NotImplementedListener();
    }

    public void handleLightEvent(LightEvent event) throws NotImplementedListener {
        throw new NotImplementedListener();
    }

    public void handleNetworkEvent(NetworkEvent event) throws NotImplementedListener {
        throw new NotImplementedListener();
    }

    public void handleRoutingEvent(RoutingEvent event) throws NotImplementedListener {
        throw new NotImplementedListener();
    }

    public void handleThemperatureEvent(TemperatureEvent event) throws NotImplementedListener {
        throw new NotImplementedListener();
    }

    public void newEventArrived(IEvent event)  {
        try {
            super.newEventArrived(event);
            if (event instanceof TemperatureEvent) {
                handleThemperatureEvent((TemperatureEvent) event);
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
