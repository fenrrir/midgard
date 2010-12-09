/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.adaptation;

import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.events.IEventHandler;
import midgard.events.IListener;
import midgard.web.json.JSONObject;

/**
 *
 * @author fenrrir
 */
public interface IAdaptationProfile extends IListener, IEventHandler {
    public void setup();
    public void clear();

    public String getProfileName();
    public void setProfileName(String name);

    public void setConfiguration(JSONObject conf);
    public void setHelper(IAdaptationProfileHelper helper);
    public JSONObject getConfiguration();
    public Vector connectToComponents();
    public Vector connectComponentsToMe();
    public Vector requiredEvents();
}
