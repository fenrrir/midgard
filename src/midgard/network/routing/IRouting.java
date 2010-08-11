/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.network.routing;

import midgard.componentmodel.IComponent;

/**
 *
 * @author fenrrir
 */
public interface IRouting extends IComponent {
    RoutingTable getRoutingTable();
    int countNeighbors();

}
