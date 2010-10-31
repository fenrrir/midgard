/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.network.routing;

import midgard.componentmodel.IComponent;
import midgard.services.IService;
import midgard.services.ProxyService;

/**
 *
 * @author fenrrir
 */
public class RoutingManager extends ProxyService implements IRoutingManager{
    private IRoutingManager concreteComponent;

    public void setConcreteComponent(IComponent component) {
        super.setConcreteComponent(component);
        this.concreteComponent = (IRoutingManager) concreteComponent;
    }




    public IRouting getRouting() {
        return concreteComponent.getRouting();
    }



}
