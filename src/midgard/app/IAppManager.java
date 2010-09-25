/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.app;

import java.util.Vector;
import midgard.services.IService;

/**
 *
 * @author fenrrir
 */
public interface IAppManager extends IService {
    public Vector getAppNames();
    public IApp getApp(String name);
    public IThreadedApp getThreadedApp(String name);
    public void loadAndInitializeApps();
    public void pauseApps();
    public void resumeApps();
    public void destroryApps();
}
