/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.pubsubhubbub.events;

import midgard.sensors.events.NetworkEvent;

/**
 *
 * @author fenrrir
 */
public class SubscriptionEvent extends NetworkEvent {

    public SubscriptionEvent(Object content) {
        super(content);
    }

}
