/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.pubsubhubbub;

import midgard.componentmodel.IComponent;
import midgard.componentmodel.ProxyComponent;

/**
 *
 * @author fenrrir
 */
public class Publisher extends ProxyComponent implements IPublisher{
    private IPublisher concreteComponent;

    public void setConcreteComponent(IComponent component){
        super.setConcreteComponent(component);
        this.concreteComponent = (IPublisher) component;
    }

    public void publish(String uri, String content) {
        concreteComponent.publish(uri, content);
    }



}
