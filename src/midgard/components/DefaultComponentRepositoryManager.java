/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.components;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import midgard.componentmodel.Component;
import midgard.componentmodel.IComponent;
import midgard.componentmodel.IProxyComponent;
import midgard.kernel.ClassLoader;
import midgard.naming.DNS;
import midgard.utils.FileUtils;
import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

/**
 *
 * @author fenrrir
 */
public class DefaultComponentRepositoryManager extends Component implements IComponentRepositoryManager{
    private final String REPO = "/midgard/components/components.json";
    private JSONObject json;
    private Hashtable table;

    


    public void clear() {
        table.clear();
    }

    public void close() {
        json = null;
        table = null;
    }

    public void destroy(){
        clear();
        close();
    }

    public void open() {
        table = new Hashtable();
        table.put(getName(), this);
        try {
            json = new JSONObject(FileUtils.readFile(REPO));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    public IComponent get(String name) {
        if (table.containsKey(name))
            return (IComponent) table.get(name);
        else{
            try {
                Object obj = ClassLoader.newInstanceOf(name);
                System.err.println("Instantiating object from class " + name);
                table.put(name, obj);
                return (IComponent) obj;
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
                return null;
            } catch (InstantiationException ex) {
                System.err.println("InstantiationException with class" + name);
                ex.printStackTrace();
                return null;
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    public void freeComponent(String name) {
        if (table.containsKey(name))
            table.remove(name);
    }



    public Vector list() {
        Vector v = new Vector();
        Enumeration e = json.keys();
        while (e.hasMoreElements()){
            try {
                String iface = (String) e.nextElement();
                JSONArray array = json.getJSONObject(iface).getJSONArray("implementations");
                for (int i=0; i <  array.length(); i++){
                    String name = (String) array.get(i);
                    if (!v.contains(name))
                        v.addElement(name);
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        return v;
    }

    public IComponent getComponent(String name) {
        return get(name);
    }

    public Vector getComponentNames() {
        return list();
    }

    public Vector getComponentsFromInterface(String name) {
        try {
            Vector v = new Vector();
            JSONArray array = json.getJSONObject(name).optJSONArray("implementations");
            for (int i = 0; i < array.length(); i++) {
                String compname = (String) array.get(i);
                if (!v.contains(compname)) {
                    v.addElement(compname);
                }
            }
            return v;

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public IComponent getImplementationOfInterface(String name) {
        try {
            JSONArray array = json.getJSONObject(name).getJSONArray("implementations");
            return get(array.getString(0));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return null;
        
    }

    public Vector getInterfaceNames() {
        Vector v = new Vector();
        Enumeration e = json.keys();
        while (e.hasMoreElements())
            v.addElement(e.nextElement());
        return v;
    }

    
    public IProxyComponent getProxyOf(String name){
        try {
            String proxyname = json.getJSONObject(name).getString("proxy");
            return (IProxyComponent) get(proxyname);
        } catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
