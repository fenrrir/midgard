/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.network.mailbox;

import java.io.IOException;

/**
 *
 * @author fenrrir
 */
public interface IReplyMessage extends IMessage {
    public void send() throws IOException;
}
