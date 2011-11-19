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

import java.io.IOException;
import midgard.kernel.Debug;

/**
 *
 * @author fenrrir
 */
public class Sender implements Runnable {

    private IMailBox mailbox;
    private boolean isRunning;

    public Sender(IMailBox mailbox) {
        this.mailbox = mailbox;
    }

    public void run() {

        isRunning = true;

        while (isRunning) {
            try {
                mailbox.waitOutboxMessages();
                for (int i = 0; i < mailbox.sizeOutbox(); i++) {
                    try {
                        IReplyMessage message = (IReplyMessage) mailbox.getOutboxMessage(i);
                        message.send();
                        mailbox.removeOutboxMessage(message);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (InterruptedException ex) {
                //ex.printStackTrace(); TODO FIX
            }
        }

    }

    public void stop(){
        isRunning = false;
    }
}
