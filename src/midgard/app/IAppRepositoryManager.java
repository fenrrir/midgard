/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.app;

import java.util.Vector;
import midgard.repositories.IRepositoryManager;

/**
 *
 * @author fenrrir
 */
public interface IAppRepositoryManager extends IRepositoryManager{
    public Vector getInstalledAppNames();
    public IApp getApp(String name);
    public IThreadedApp getThreadedApp(String name);
}
