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

    private final String REPO = "/apps.json";
    private Vector names;
    private JSONObject json;
    private IComponentManager componentManager = null;

    public DefaultAppManager() {
    }



    public String[] getRequiredInterfaces() {
        return new String[]{DNS.ICOMPONENTMANAGER};
    }

    public void connect(String interfaceName, IComponent component) {
        super.connect(interfaceName, component);
        componentManager = (IComponentManager) component;

    }

    private void loadData() {
        try {
            String name, classname;
            names = new Vector();
            json = new JSONObject(FileUtils.readFile(REPO));
            Enumeration e = json.keys();
            while (e.hasMoreElements()) {
                name = (String) e.nextElement();
                classname = json.getJSONObject(name).getString("class");
                names.addElement(classname);
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    private void eraseData(){
        names.removeAllElements();
        names = null;
        json = null;
    }

    public void initialize() {
        super.initialize();
        loadData();
    }

    public void destroy() {
        super.destroy();
        eraseData();
    }

    public void pause() {
        super.pause();
        eraseData();
    }

    public void resume() {
        super.resume();
        loadData();
    }



    public void destroryApps() {
        IApp app;

        for (int i = 0; i < names.size(); i++) {
            app = getApp((String) names.elementAt(i));
            app.destroy();

        }
    }

    public IApp getApp(String name) {
        return (IApp) componentManager.resolveComponent(name);
    }

    public Vector getAppNames() {
        return names;
    }

    public IThreadedApp getThreadedApp(String name) {
        return (IThreadedApp) componentManager.resolveComponent(name);
    }

    public void loadAndInitializeApps() {
        IApp app;
        Thread thread;

        for (int i = 0; i < names.size(); i++) {
            app = getApp((String) names.elementAt(i));
            if (app instanceof ThreadedApp){
                thread = new Thread( (Runnable) app);
                thread.start();
            }
        }
    }

    public void pauseApps() {
        IApp app;

        for (int i = 0; i < names.size(); i++) {
            app = getApp((String) names.elementAt(i));
            app.pause();

        }
    }

    public void resumeApps() {
        IApp app;

        for (int i = 0; i < names.size(); i++) {
            app = getApp((String) names.elementAt(i));
            app.resume();

        }
    }
}
