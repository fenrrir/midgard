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
public class ResponseEvent extends NetworkEvent {

    public ResponseEvent(Object content) {
        super(content);
    }

}
