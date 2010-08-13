/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.network.routing;

import midgard.services.Service;

/**
 *
 * @author fenrrir
 */
public class LQRPRouting extends Service implements IRouting {

    public int countNeighbors() {
        return 0;
    }

    public RoutingTable getRoutingTable() {
        return new RoutingTable();
    }
}
