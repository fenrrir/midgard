/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midgard.app;

import java.util.Enumeration;
import java.util.Vector;
import midgard.componentmodel.Component;
import midgard.componentmodel.IComponent;
import midgard.components.IComponentManager;
import midgard.naming.DNS;
import midgard.utils.FileUtils;
import org.json.me.JSONException;
import org.json.me.JSONObject;

/**
 *
 * @author fenrrir
 */
public class DefaultAppRepositoryManager extends Component implements IAppRepositoryManager {

    private IComponentManager componentManager = null;
    private final String REPO = "/apps.json";
    private Vector names;
    private JSONObject json;

    public String[] getRequiredInterfaces() {
        return new String[]{DNS.ICOMPONENTMANAGER};
    }

    public void connect(String interfaceName, IComponent component) {
        super.connect(interfaceName, component);
        componentManager = (IComponentManager) component;

    }

    public IApp getApp(String name) {
        return (IApp) get(name);
    }

    public Vector getInstalledAppNames() {
        return names;
    }

    public IThreadedApp getThreadedApp(String name) {
        return (IThreadedApp) get(name);
    }

    public void clear() {
        names.removeAllElements();
        names = null;
        json = null;
    }

    public void close() {
        clear();
    }

    public IComponent get(String name) {
        return (IComponent) componentManager.resolveComponent(name);
    }

    public Vector list() {
        Vector v1, v2;
        Enumeration e;
        v1 = componentManager.getComponentsFromInterface(DNS.IAPP);
        v2 = componentManager.getComponentsFromInterface(DNS.ITHREADEDAPP);

        e = v2.elements();
        while (e.hasMoreElements()) {
            v1.addElement(e.nextElement());
        }

        return v1;

    }

    public void open() {
        if (json == null) {
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
    }

    public void destroy() {
        super.destroy();
        close();
    }

    public void pause() {
        super.pause();
        close();
    }

    public void resume() {
        super.resume();
        open();
    }
}
