/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.components;

import com.sun.spot.util.Utils;
import java.util.Hashtable;
import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.kernel.MicroKernel;

/**
 *
 * @author fenrrir
 */
public class DefaultComponentProfile implements IComponentProfile {
    private Hashtable cache;
    private String name;

    public DefaultComponentProfile(String name, Hashtable cache) {
        this.name = name;
        this.cache = cache;
    }



    public String getProfileName() {
        return name;
    }

    public IComponent getComponent(String name) {
        return MicroKernel.getInstance().getNameService().resolveName(name);
    }

    public Vector getComponentNames() {
        return Utils.enumToVector(cache.elements());
    }

    public Vector getComponentsFromInterface(String name) {
        Vector v = new Vector();
        v.addElement( cache.get(name)  );
        return v;
    }

    public IComponent getImplementationOfInterface(String name) {
        String componentName = (String) getComponentsFromInterface(name).elementAt(0);
        return getComponent(componentName);
    }

    public Vector getInterfaceNames() {
        return Utils.enumToVector(cache.keys());
    }



}
