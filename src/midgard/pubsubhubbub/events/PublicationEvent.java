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
public class PublicationEvent extends NetworkEvent{

    public PublicationEvent(Object content) {
        super(content);
    }


}
