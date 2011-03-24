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

import com.sun.spot.ipv6.tcp.ServerSocket;
import com.sun.spot.ipv6.tcp.Socket;
import java.io.IOException;
import midgard.kernel.Debug;

/**
 *
 * @author fenrrir
 */
public class Receiver implements Runnable {

    private IMailBox mailbox;
    private ServerSocket serverSocket;
    private Readers readers;
    private boolean isRunning;

    public Receiver(IMailBox mailbox) {
        this.mailbox = mailbox;
        readers = new Readers(5, mailbox);

    }

    public void run() {

        isRunning = true;

        try {
            serverSocket = new ServerSocket(80);
        } catch (IOException e) {
            Debug.debug("Could not listen on port: 80.", 1);
            e.printStackTrace();
        }
        Debug.debug("[APP] Socket opened and listening", 1);
        while (isRunning) {
            try {
                IReceivedMessage message =  new Message((Socket) serverSocket.accept(), mailbox);
                readers.addMessage(message);
                Debug.debug("[APP] Connection started.", 1);
            } catch (IOException ex) {
                try {
                    serverSocket.close();
                    Debug.debug("[APP] Done, closing up", 1);
                } catch (IOException ex2) {
                    ex2.printStackTrace();
                }
            }

        }

    }

    public void stop(){
        isRunning = false;
        readers.stop();
    }
}
