/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.pubsubhubbub;

import midgard.componentmodel.IComponent;
import midgard.events.IListener;

/**
 *
 * @author fenrrir
 */
public interface ISubscriber extends IComponent {
    public void register(IListener listener, String topic);
    public void unRegister(IListener listener, String topic);

    public void register(IListener listener, String topic, String address);
    public void unRegister(IListener listener, String topic, String address);
}
