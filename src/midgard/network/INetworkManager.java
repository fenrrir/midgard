/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.network;

import midgard.network.routing.RoutingManager;
import midgard.services.IService;

/**
 *
 * @author fenrrir
 */
public interface INetworkManager extends IService {
    RoutingManager getRoutingManager();

}
