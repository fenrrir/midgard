/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midgard.network.mailbox;

import java.util.Vector;
import midgard.kernel.Debug;

/**
 *
 * @author fenrrir
 */
public class Box extends Vector {

    public synchronized void addElement(Object obj) {
        super.addElement(obj);
        notifyAll();
    }

    public synchronized void waitMessages() throws InterruptedException {
        if (size() == 0)
            wait();
    }

    public synchronized Object elementAt(int index) {
        return super.elementAt(index);
    }

    public synchronized boolean removeElement(Object obj) {
        return super.removeElement(obj);
    }

    public synchronized int size() {
        return super.size();
    }


}
