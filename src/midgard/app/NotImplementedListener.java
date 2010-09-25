/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.app;

/**
 *
 * @author fenrrir
 */
public class NotImplementedListener extends Exception {

    /**
     * Creates a new instance of <code>NotImplementedListener</code> without detail message.
     */
    public NotImplementedListener() {
    }


    /**
     * Constructs an instance of <code>NotImplementedListener</code> with the specified detail message.
     * @param msg the detail message.
     */
    public NotImplementedListener(String msg) {
        super(msg);
    }
}
