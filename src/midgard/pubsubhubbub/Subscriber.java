/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.pubsubhubbub;

import midgard.componentmodel.IComponent;
import midgard.componentmodel.ProxyComponent;
import midgard.events.IListener;

/**
 *
 * @author fenrrir
 */
public class Subscriber extends ProxyComponent implements ISubscriber {
    private ISubscriber concreteComponent;

    public void setConcreteComponent( IComponent component ){
        super.setConcreteComponent(component);
        this.concreteComponent = (ISubscriber) component;
    }

    public void unRegister(IListener listener, String topic) {
        concreteComponent.unRegister(listener, topic);
    }

    public void register(IListener listener, String topic) {
        concreteComponent.register(listener, topic);
    }

    public void unRegister(IListener listener, String topic, String address) {
        concreteComponent.unRegister(listener, topic, address);
    }

    public void register(IListener listener, String topic, String address) {
        concreteComponent.register(listener, topic, address);
    }

    
}
