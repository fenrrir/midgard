/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web.events;

import midgard.sensors.events.NetworkEvent;

/**
 *
 * @author fenrrir
 */
public class RequestEvent extends NetworkEvent{

    public RequestEvent(Object content) {
        super(content);
    }

}