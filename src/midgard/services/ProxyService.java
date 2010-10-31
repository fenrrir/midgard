/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.services;

import midgard.componentmodel.IComponent;
import midgard.componentmodel.ProxyComponent;

/**
 *
 * @author fenrrir
 */
public class ProxyService extends ProxyComponent implements IProxyService{
    private IService concreteComponent;


    public ProxyService(){
    }

    public void setConcreteComponent(IComponent component) {
        super.setConcreteComponent(component);
        this.concreteComponent = (IService) component;
    }

    public void stopService() {
        concreteComponent.stopService();
    }

    public void startService() {
        concreteComponent.startService();
    }



}
