/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.network.mailbox;

import midgard.componentmodel.IComponent;

/**
 *
 * @author fenrrir
 */
public interface IMailBox {
    public IMessage getInboxMessage(int index);
    public IReplyMessage getOutboxMessage(int index);
    public void removeInboxMessage(IMessage message);
    public void removeOutboxMessage(IReplyMessage message);
    public void putOutboxMessage(IReplyMessage message);
    public void putInboxMessage(IMessage message);
    public int sizeInbox();
    public int sizeOutbox();
}
