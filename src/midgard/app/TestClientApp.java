/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.app;

import java.io.IOException;
import midgard.web.http.HttpConnector;

/**
 *
 * @author fenrrir
 */
public class TestClientApp extends App {

    public void destroy() {
        super.destroy();
        System.err.println(getName() + " destroy");
    }

    public void initialize() {
        super.initialize();
        System.err.println("@@@@@@ " + getName() + " load");
        HttpConnector conn = new HttpConnector();
        try {
            conn.connect("c0a8.0f66.0000.1001");
            String result = conn.get("/foobar");
            System.err.println("@@@@ Reposta eh " + result );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    public void load() {
        super.load();
        System.err.println("@@@@@@ " + getName() + " load");
    }

    public void pause() {
        super.pause();
        System.err.println(getName() + " pause");
    }

    public void resume() {
        super.resume();
        System.err.println(getName() + " resume");
    }

}
