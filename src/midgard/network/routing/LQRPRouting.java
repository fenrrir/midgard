/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.network.routing;

import midgard.componentmodel.Component;
import midgard.events.IEvent;

/**
 *
 * @author fenrrir
 */
public class LQRPRouting extends Component implements IRouting {

    public int countNeighbors() {
        return 0;
    }

    public RoutingTable getRoutingTable() {
        return new RoutingTable();
    }

    public void destroy() {
    }

    public void initialize() {
    }

    public void load() {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void newEventArrived(IEvent event) {
    }



}
