/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.network;

import midgard.network.routing.RoutingManager;
import midgard.services.Service;

/**
 *
 * @author fenrrir
 */
public class DefaultNetworkManager extends Service implements INetworkManager{

    public RoutingManager getRoutingManager() {
        return null;
    }

}
