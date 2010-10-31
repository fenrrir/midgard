/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midgard.app;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import midgard.componentmodel.Component;
import midgard.componentmodel.IComponent;
import midgard.components.IComponentManager;
import midgard.naming.DNS;
import midgard.utils.FileUtils;
import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

/**
 *
 * @author fenrrir
 */
public class DefaultAppRepositoryManager extends Component implements IAppRepositoryManager {

    private IComponentManager componentManager = null;
    private final String REPO = "/apps.json";
    private Vector names, sensors, services;
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
        services.removeAllElements();
        sensors.removeAllElements();
        services = null;
        sensors = null;
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
        JSONObject apps;
        JSONArray jsonservices, jsonsensors;
        String name, classname;
        String sensorinterface, sensorclass;

        if (json == null) {
            try {
                
                names = new Vector();
                json = new JSONObject(FileUtils.readFile(REPO));
                apps = json.getJSONObject("apps");
                Enumeration e = apps.keys();
                while (e.hasMoreElements()) {
                    name = (String) e.nextElement();
                    classname = apps.getJSONObject(name).getString("class");
                    names.addElement(classname);
                }


                sensors = new Vector();
                jsonsensors = json.getJSONArray("sensors");
                int size = jsonsensors.length();
                for (int i=0; i< size; i++) {
                    sensors.addElement( jsonsensors.get(i) );
                }

                services = new Vector();
                jsonservices = json.getJSONArray("services");
                size = jsonservices.length();
                for (int i=0; i< size; i++) {
                    services.addElement( jsonservices.get(i) );
                }

             
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
    }

    public Vector listSensors() {
        return sensors;
    }

    public Vector listServices() {
        return services;
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
