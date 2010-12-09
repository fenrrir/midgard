/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.adaptation;

import java.util.Vector;
import midgard.componentmodel.IComponent;

/**
 *
 * @author fenrrir
 */
public interface IAdaptationProfileManager extends IComponent, IAdaptationProfileHelper {
    public IAdaptationProfile getProfile(String name);
    public Vector listProfiles();
    public void loadProfile(String name);
    public void unloadProfile(String name);

}
