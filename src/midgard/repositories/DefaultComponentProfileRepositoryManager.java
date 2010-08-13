/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

import java.util.Vector;
import midgard.componentmodel.Component;
import midgard.componentmodel.IComponent;
import midgard.components.IComponentProfile;

/**
 *
 * @author fenrrir
 */
public class DefaultComponentProfileRepositoryManager extends Component implements IComponentProfileRepositoryManager {

    public void clear() {
    }

    public void close() {
    }

    public void open() {
    }

    public IComponent get(String name) {
        return null;
    }

    public Vector list() {
        return null;
    }

    public String getContainerName() {
        return getName();
    }

    public IComponentProfile getProfileByName(String name) {
        return (IComponentProfile) get(name);
    }

    public Vector getProfiles() {
        return list();
    }


}
