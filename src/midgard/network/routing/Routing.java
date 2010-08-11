/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.network.routing;

import midgard.componentmodel.IComponent;
import midgard.componentmodel.ProxyComponent;

/**
 *
 * @author fenrrir
 */
public class Routing extends ProxyComponent implements IRouting {
    private IRouting concreteComponent;

    public Routing(IRouting concreteComponent) {
        super( (IComponent) concreteComponent );
        this.concreteComponent = concreteComponent;
    }


    public RoutingTable getRoutingTable() {
        return concreteComponent.getRoutingTable();
    }

    public int countNeighbors() {
        return concreteComponent.countNeighbors();
    }


}
