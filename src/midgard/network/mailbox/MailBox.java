/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.network.mailbox;

import java.util.Vector;

/**
 *
 * @author fenrrir
 */
public class MailBox implements IMailBox{
    private Vector inbox, outbox;

    public MailBox(){
        inbox = new Vector();
        outbox = new Vector();
    }


    public synchronized void putOutboxMessage(IReplyMessage message) {
        outbox.addElement(message);
        notify();
    }

    public synchronized void putInboxMessage(IMessage message) {
        inbox.addElement(message);
    }

    public synchronized int sizeInbox() {
        return inbox.size();
    }

    public synchronized int sizeOutbox() {
        return outbox.size();
    }

    public synchronized IMessage getInboxMessage(int index) {
        return (IMessage)inbox.elementAt(index);
    }

    public synchronized IReplyMessage getOutboxMessage(int index) {
        return (IReplyMessage)outbox.elementAt(index);
    }

    public synchronized void removeInboxMessage(IMessage message) {
        inbox.removeElement(message);
    }

    public synchronized void removeOutboxMessage(IReplyMessage message) {
        outbox.removeElement(message);
    }

}
