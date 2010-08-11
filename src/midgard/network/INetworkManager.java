/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.network;

import midgard.componentmodel.IComponent;
import midgard.network.routing.RoutingManager;

/**
 *
 * @author fenrrir
 */
public interface INetworkManager extends IComponent {
    RoutingManager getRoutingManager();

}
