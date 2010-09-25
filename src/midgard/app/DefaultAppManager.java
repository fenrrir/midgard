/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midgard.app;

import java.util.Enumeration;
import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.components.IComponentManager;
import midgard.naming.DNS;
import midgard.services.Service;
import midgard.utils.FileUtils;
import org.json.me.JSONException;
import org.json.me.JSONObject;

/**
 *
 * @author fenrrir
 */
public class DefaultAppManager extends Service implements IAppManager {

    private IAppRepositoryManager repository = null;

    public DefaultAppManager() {
    }



    public String[] getRequiredInterfaces() {
        return new String[]{DNS.IAPPREPOSITORYMANAGER};
    }

    public void connect(String interfaceName, IComponent component) {
        super.connect(interfaceName, component);
        repository = (IAppRepositoryManager) component;
    }



    public void initialize() {
        super.initialize();
        repository.open();
    }

    public void destroryApps() {

        IApp app;
        Vector names = repository.getInstalledAppNames();

        for (int i = 0; i < names.size(); i++) {
            app = repository.getApp((String) names.elementAt(i));
            app.destroy();

        }
    }

    public void loadAndInitializeApps() {
        IApp app;
        Thread thread;

        Vector names = repository.getInstalledAppNames();

        for (int i = 0; i < names.size(); i++) {
            app = repository.getApp((String) names.elementAt(i));
            if (app instanceof ThreadedApp){
                thread = new Thread( (Runnable) app);
                thread.start();
            }
        }
    }

    public void pauseApps() {
        IApp app;

        Vector names = repository.getInstalledAppNames();

        for (int i = 0; i < names.size(); i++) {
            app = repository.getApp((String) names.elementAt(i));
            app.pause();

        }
    }

    public void resumeApps() {
        IApp app;

        Vector names = repository.getInstalledAppNames();

        for (int i = 0; i < names.size(); i++) {
            app = repository.getApp((String) names.elementAt(i));
            app.resume();

        }
    }
}
