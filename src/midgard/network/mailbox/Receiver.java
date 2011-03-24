/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
