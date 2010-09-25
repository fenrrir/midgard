/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.app;

/**
 *
 * @author fenrrir
 */
public class TestApp extends App{

    public void destroy() {
        super.destroy();
        System.err.println(getName() + " destroy");
    }

    public void initialize() {
        super.initialize();
        System.err.println("@@@@@ " + getName() + " initialize");
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
