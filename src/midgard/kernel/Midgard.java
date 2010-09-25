/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.kernel;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/**
 *
 * @author fenrrir
 */
public class Midgard extends MIDlet{
    private MicroKernel kernel;

    protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
        
    }

    protected void pauseApp() {
    }

    protected void startApp() throws MIDletStateChangeException {
        kernel = MicroKernel.getInstance();
    }
    

}
