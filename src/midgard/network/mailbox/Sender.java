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
public class Sender implements Runnable {

    private IMailBox mailbox;
    private boolean isRunning;

    public Sender(IMailBox mailbox) {
        this.mailbox = mailbox;
    }

    public void run() {

        isRunning = true;

        while (isRunning) {

            for (int i = 0; i < mailbox.sizeOutbox(); i++) {
                try {
                    IReplyMessage message = (IReplyMessage) mailbox.getOutboxMessage(i);
                    message.send();
                    mailbox.removeOutboxMessage(message);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    public void stop(){
        isRunning = false;
    }
}
