/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.components;

import java.util.Vector;

/**
 *
 * @author fenrrir
 */
public interface IComponentProfileContainer {
    public String getContainerName();
    public Vector getProfiles();
    public IComponentProfile getProfileByName(String name);

}
