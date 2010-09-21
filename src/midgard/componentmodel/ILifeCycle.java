/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.componentmodel;

/**
 *
 * @author fenrrir
 */
public interface ILifeCycle {
    void load();
    void initialize();
    void pause();
    void resume();
    void destroy();
    public boolean isInitialized();
    public boolean isPaused();
    public boolean isLoaded();
}
