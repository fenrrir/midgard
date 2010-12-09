/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midgard.repositories;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import midgard.adaptation.AdaptationProfile;
import midgard.adaptation.IAdaptationProfile;
import midgard.componentmodel.Component;
import midgard.componentmodel.IComponent;
import midgard.utils.FileUtils;
import midgard.web.json.JSONException;
import midgard.web.json.JSONObject;

/**
 *
 * @author fenrrir
 */
public class DefaultAdaptationProfileRepositoryManager extends Component implements IAdaptationProfileRepositoryManager {

    private final String REPO = "/midgard/adaptation/profiles.json";
    private JSONObject json;
    private boolean isOpened = false;
    private Hashtable cache = null;

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
                json = new JSONObject(FileUtils.readFile(REPO));
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
