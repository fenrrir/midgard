/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.network;

import midgard.componentmodel.IComponent;
import midgard.network.routing.RoutingManager;
import midgard.services.IService;
import midgard.services.ProxyService;

/**
 *
 * @author fenrrir
 */
public class NetworkManager extends ProxyService implements INetworkManager{
    private INetworkManager concreteComponent;

    public void setConcreteComponent(IComponent component) {
        super.setConcreteComponent(component);
        this.concreteComponent = (INetworkManager) component;
    }

    

    public RoutingManager getRoutingManager() {
        return concreteComponent.getRoutingManager();
    }



}
