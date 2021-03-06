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
package midgard.repositories.components;

import midgard.repositories.components.IComponentProfileRepositoryManager;
import com.sun.spot.util.Utils;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import midgard.componentmodel.Component;
import midgard.componentmodel.IComponent;
import midgard.components.DefaultComponentProfile;
import midgard.components.IComponentProfile;
import midgard.components.IComponentRepositoryManager;
import midgard.utils.FileUtils;
import midgard.web.json.JSONException;
import midgard.web.json.JSONObject;

/**
 *
 * @author fenrrir
 */
public class DefaultComponentProfileRepositoryManager
        extends Component implements IComponentProfileRepositoryManager {

    private boolean isOpened;
    private IComponentRepositoryManager repository = null;
    private final String REPO = "/midgard/components/profiles.json";
    private JSONObject json;
    private Hashtable profileCache;

    public String[] getRequiredInterfaces() {
        return new String[]{IComponentRepositoryManager.class.getName()};
    }

    public void initialize() {
        super.initialize();
        repository = (IComponentRepositoryManager) getConnectedComponents().
                get(IComponentRepositoryManager.class.getName());

        isOpened = false;
    }

    public void clear() {
        json = null;
        profileCache.clear();
    }

    public void close() {
        clear();
    }

    public void open() {
        if (!isOpened) {
            profileCache = new Hashtable();
            try {
                json = new JSONObject(FileUtils.readFile(REPO));
                Vector interfaces = repository.getInterfaceNames();
                String defaultImpl, interfaceName;
                Hashtable defaultProfile = new Hashtable();

                for(int i=0; i< interfaces.size(); i++){
                    interfaceName = (String) interfaces.elementAt(i);
                    defaultImpl = (String)
                            repository
                                .getComponentsFromInterface(interfaceName)
                                    .firstElement();
                    defaultProfile.put(interfaceName, defaultImpl);
                }

                IComponentProfile componentProfile =
                        new DefaultComponentProfile("default", defaultProfile);
                profileCache.put("default", componentProfile);

                isOpened = true;
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
    }

    public IComponent get(String name) {
        return (IComponent) profileCache.get(name);
    }

    public Vector list() {
        return Utils.enumToVector(json.keys());
    }

    public String getContainerName() {
        return getName();
    }

    public IComponentProfile getProfileByName(String name) {
        try {
            if (profileCache.containsKey(name)) {
                return (IComponentProfile) profileCache.get(name);
            }

            JSONObject profile = json.getJSONObject(name);
            Hashtable cache = new Hashtable();
            Enumeration interfaces = profile.keys();

            while (interfaces.hasMoreElements()) {
                Object key = interfaces.nextElement();
                cache.put(key, cache.get(key));
            }

            IComponentProfile componentProfile = new DefaultComponentProfile(name, cache);
            profileCache.put(name, componentProfile);
            return componentProfile;
        } catch (JSONException ex) {
            return null;
        }
    }

    public Vector getProfiles() {
        return list();
    }
}
