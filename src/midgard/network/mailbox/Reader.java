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

import java.util.Vector;
import midgard.kernel.Debug;

/**
 *
 * @author fenrrir
 */
public class Reader implements Runnable{
    private IMailBox mailbox;
    private Vector messages;
    private boolean isRunning;
    private Thread thread;

    public Reader(IMailBox mailbox){
        this.mailbox = mailbox;
        messages = new Vector();
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void addMessage(IReceivedMessage message){
        messages.addElement(message);
        notify();
    }

    private IReceivedMessage getMessage(){
        IReceivedMessage message = (IReceivedMessage) messages.elementAt(0);
        messages.removeElementAt(0);
        return message;
    }

    public synchronized void run() {

        isRunning = true;
        while(isRunning){
            if(messages.size()==0){
                try {
                    wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            if (!isRunning){
                break;
            }

            IReceivedMessage message = getMessage();
            message.read();
            mailbox.putInboxMessage((IMessage)message);
        }
    }

    public void stop(){
        isRunning = false;
        notify();
    }

}
