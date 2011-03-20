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
package midgard.repositories.adaptation;

import midgard.repositories.adaptation.IAdaptationProfileRepositoryManager;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import midgard.adaptation.AdaptationProfile;
import midgard.adaptation.IAdaptationProfile;
import midgard.componentmodel.Component;
import midgard.componentmodel.IComponent;
import midgard.config.IDefaultConfig;
import midgard.utils.FileUtils;
import midgard.web.json.JSONException;
import midgard.web.json.JSONObject;

/**
 *
 * @author fenrrir
 */
public class DefaultAdaptationProfileRepositoryManager extends Component implements IAdaptationProfileRepositoryManager {

    private String repositoryPath;
    private IDefaultConfig defaultConfig = null;
    private JSONObject json;
    private boolean isOpened = false;
    private Hashtable cache = null;


    public String[] getRequiredInterfaces() {
        return new String[]{
                    IDefaultConfig.class.getName()
                };
    }

    public void initialize() {
        super.initialize();
        defaultConfig = (IDefaultConfig) getConnectedComponents().get(IDefaultConfig.class.getName());
        repositoryPath = defaultConfig.getAdaptationProfileRepositoryPath();
    }

    public void clear() {
        json = null;
        isOpened = false;
        cache.clear();
        cache = null;
    }

    public void close() {
        json = null;
        isOpened = false;
        cache.clear();
        cache = null;
    }

    public void open() {
        if (!isOpened) {
            try {
                cache = new Hashtable();
                json = new JSONObject(FileUtils.readFile(repositoryPath));
                isOpened = true;
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
    }

    public IAdaptationProfile getProfile(String name) {
        try {

            if (cache.containsKey(name)){
                return (IAdaptationProfile) cache.get(name);
            }
            
            JSONObject conf = json.getJSONObject(name);
            AdaptationProfile profile = new AdaptationProfile();
            profile.setConfiguration(conf);
            profile.setProfileName(name);
            profile.setup();
            return profile;
        } catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Vector list() {
        if (isOpened) {
            Vector profiles = new Vector();
            Enumeration names = json.keys();
            while(names.hasMoreElements()){
                profiles.addElement( names.nextElement()  );
            }
            return profiles;
        }
        return null;
    }

    public void freeProfile(String name) {
        if (cache.containsKey(name)){
            cache.remove(name);
        }
    }

    public IComponent get(String name) {
        return null;
    }


}
