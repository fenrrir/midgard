/*
* Copyright (C) 2011 Rodrigo Pinheiro Marques de Araujo
*
* This program is free software; you can redistribute it and/or modify it under
* the terms of the GNU General Public License as published by the Free Software
* Foundation; either version 2 of the License, or (at your option) any later
* version.
*
* This program is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
* FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
* details.
*
* You should have received a copy of the GNU General Public License along with
* this program; if not, write to the Free Software Foundation, Inc., 51
* Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
*/

package midgard.network.mailbox;

import midgard.kernel.Debug;

/**
 *
 * @author fenrrir
 */
public class MailBox implements IMailBox{
    private Box inbox, outbox;

    public MailBox(){
        inbox = new Box();
        outbox = new Box();
    }


    public void putOutboxMessage(IReplyMessage message) {
        outbox.addElement(message);
    }

    public void putInboxMessage(IMessage message) {
        inbox.addElement(message);
    }

    public int sizeInbox() {
        return inbox.size();
    }

    public int sizeOutbox() {
        return outbox.size();
    }

    public IMessage getInboxMessage(int index) {
        return (IMessage)inbox.elementAt(index);
    }

    public IReplyMessage getOutboxMessage(int index) {
        return (IReplyMessage)outbox.elementAt(index);
    }

    public void removeInboxMessage(IMessage message) {
        inbox.removeElement(message);
    }

    public void removeOutboxMessage(IReplyMessage message) {
        outbox.removeElement(message);
    }

    public void waitOutboxMessages() throws InterruptedException  {
        outbox.waitMessages();
    }

    public void waitInboxMessages() throws InterruptedException  {
        inbox.waitMessages();
    }

}
