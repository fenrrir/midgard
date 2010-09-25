/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.components.IComponentProfile;

/**
 *
 * @author fenrrir
 */
public class ComponentProfileRepositoryManager extends RepositoryManager implements IComponentProfileRepositoryManager{
    private IComponentProfileRepositoryManager concreteComponent;




    public void setConcreteComponent(IComponent concreteComponent){
        super.setConcreteComponent(concreteComponent);
        this.concreteComponent = (IComponentProfileRepositoryManager) concreteComponent;
    }

    public Vector getProfiles() {
        return concreteComponent.getProfiles();
    }

    public IComponentProfile getProfileByName(String name) {
        return concreteComponent.getProfileByName(name);
    }

    public String getContainerName() {
        return concreteComponent.getContainerName();
    }

    

}
