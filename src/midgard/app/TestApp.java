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
        System.out.print(getName() + " destroy");
    }

    public void initialize() {
        super.initialize();
        System.out.print(getName() + " initialize");
    }

    public void load() {
        super.load();
        System.out.print(getName() + " load");
    }

    public void pause() {
        super.pause();
        System.out.print(getName() + " pause");
    }

    public void resume() {
        super.resume();
        System.out.print(getName() + " resume");
    }
}
