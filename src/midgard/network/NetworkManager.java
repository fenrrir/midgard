/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.network;

import midgard.network.routing.RoutingManager;
import midgard.services.IService;
import midgard.services.ProxyService;

/**
 *
 * @author fenrrir
 */
public class NetworkManager extends ProxyService implements INetworkManager{
    private INetworkManager concreteComponent;

    public NetworkManager(INetworkManager concreteComponent) {
        super((IService) concreteComponent);
        this.concreteComponent = concreteComponent;
    }

    public RoutingManager getRoutingManager() {
        return concreteComponent.getRoutingManager();
    }



}
