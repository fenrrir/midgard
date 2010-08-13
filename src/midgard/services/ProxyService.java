/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.services;

import midgard.componentmodel.ProxyComponent;

/**
 *
 * @author fenrrir
 */
public class ProxyService extends ProxyComponent implements IProxyService{
    private IService concreteComponent;

    public ProxyService(IService concreteComponent) {
        super(concreteComponent);
        this.concreteComponent =  concreteComponent;
    }

}
