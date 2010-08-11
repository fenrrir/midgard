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
public class RoutingManager extends ProxyComponent implements IRoutingManager{
    private IRoutingManager concreteComponent;

    public RoutingManager(IRoutingManager concreteComponent) {
        super((IComponent) concreteComponent);
        this.concreteComponent = concreteComponent;
    }

    public IRouting getRouting() {
        return concreteComponent.getRouting();
    }



}
