/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web.events;

import midgard.events.Event;
import midgard.sensors.events.NetworkEvent;

/**
 *
 * @author fenrrir
 */
public class AsyncMessageEvent extends NetworkEvent {

    public AsyncMessageEvent(Object content) {
        super(content);
    }
    

}
