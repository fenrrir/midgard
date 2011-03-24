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
