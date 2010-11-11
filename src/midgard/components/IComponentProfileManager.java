/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.components;

import midgard.componentmodel.IComponent;
import midgard.componentmodel.IProxyComponent;

/**
 *
 * @author fenrrir
 */
public interface IComponentProfileManager extends IComponent, IComponentProfileContainer {
    public IComponentProfile getActiveProfile();
    public void loadAndinitializeProfile(String profile);
    public void loadAndinitializeProfile(IComponentProfile profile);
    public void resumeProfile(String profile);
    public void resumeProfile(IComponentProfile profile);
    public void pauseProfile(String profile);
    public void pauseProfile(IComponentProfile profile);
    public void destroyProfile(String profile);
    public void destroyProfile(IComponentProfile profile);
    public void changeProfile(String old,
                                     String newProfile);
    public void changeProfile(IComponentProfile profile,
                              IComponentProfile newProfile);
    
}
