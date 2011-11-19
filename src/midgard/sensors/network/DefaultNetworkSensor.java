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

package midgard.sensors.network;

import midgard.kernel.Debug;
import midgard.network.mailbox.IMailBox;
import midgard.network.mailbox.IMessage;
import midgard.network.mailbox.MailBox;
import midgard.network.mailbox.Receiver;
import midgard.network.mailbox.Sender;
import midgard.sensors.Sensor;
import midgard.sensors.events.NetworkEvent;




/**
 *
 * @author fenrrir
 */
public class DefaultNetworkSensor extends Sensor implements INetworkSensor, Runnable{

    private IMailBox mailbox;
    private Sender sender;
    private Receiver receiver;
    private Thread receiverThread, senderThread, self;
    private boolean isRunning = false;



    public void initialize(){
        mailbox = new MailBox();
        super.initialize();
    }

    public void collect() {
    }

    public void disableSensor() {
        isRunning = false;
        self.interrupt();
        sender.stop();
        receiver.stop();
        senderThread.interrupt();
        receiverThread.interrupt();
        mailbox = null;
        receiverThread = null;
        senderThread = null;
    }

    public void initSensor() {
        sender =  new Sender(mailbox);
        receiver = new Receiver(mailbox);
        receiverThread = new Thread(receiver);
        senderThread = new Thread(sender);

        self = new Thread(this);


        receiverThread.start();
        senderThread.start();
        self.start();
    }

    public void run(){
        isRunning = true;
        while(isRunning){
            try {
                mailbox.waitInboxMessages();
                for (int i = 0; i < mailbox.sizeInbox(); i++) {
                    IMessage message = mailbox.getInboxMessage(i);
                    fireEvent(new NetworkEvent(message));
                }
            } catch (InterruptedException ex) {
                //ex.printStackTrace(); FIX TODO
            }
        }
    }
   
}
