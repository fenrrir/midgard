/*
* Copyright (C) 2011 Rodrigo Pinheiro Marques de Araujo
*
* This program is free software; you can redistribute it and/or modify it under
* the terms of the GNU General Public License as published by the Free Software
* Foundation; either version 2 of the License, or (at your option) any later
* version.
*
* This program is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
* FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
* details.
*
* You should have received a copy of the GNU General Public License along with
* this program; if not, write to the Free Software Foundation, Inc., 51
* Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
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

    public void freeComponent(String name) {
        if (cache.containsKey(name))
            cache.remove(name);
    }


}
