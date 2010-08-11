/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.network;

import midgard.componentmodel.IComponent;
import midgard.componentmodel.ProxyComponent;
import midgard.network.routing.RoutingManager;

/**
 *
 * @author fenrrir
 */
public class NetworkManager extends ProxyComponent implements INetworkManager{
    private INetworkManager concreteComponent;

    public NetworkManager(INetworkManager concreteComponent) {
        super((IComponent) concreteComponent);
        this.concreteComponent = concreteComponent;
    }

    public RoutingManager getRoutingManager() {
        return concreteComponent.getRoutingManager();
    }



}
