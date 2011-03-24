/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.network.mailbox;

/**
 *
 * @author fenrrir
 */
public class Readers {
    private IMailBox mailbox;
    private Reader [] readers;
    private int size;
    private int initializedThreads;
    private int nextThread;

    public Readers(int size, IMailBox mailbox) {
        this.mailbox = mailbox;
        this.size = size;
        nextThread = -1;
        readers = new Reader[size];
        initializedThreads = -1;
    }

    public Reader getNextReader(){
        //round robin
        nextThread++;
        if (nextThread >= size){
            nextThread = 0;
        }

        if(nextThread > initializedThreads){
            readers[nextThread] = new Reader(mailbox);
        }


        return readers[nextThread];
    }

    public void addMessage(IReceivedMessage message){
        Reader reader = getNextReader();
        reader.addMessage(message);
    }

    public void stop(){
        for(int i=0; i< size; i++){
            Reader reader = readers[i];
            reader.stop();
        }
    }

}
