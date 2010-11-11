/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.components;

import java.util.Vector;
import midgard.componentmodel.Component;
import midgard.componentmodel.IComponent;
import midgard.repositories.IComponentProfileRepositoryManager;

/**
 *
 * @author fenrrir
 */
public class DefaultComponentProfileManager extends Component
       implements IComponentProfileManager {

    private IComponentProfileRepositoryManager repository;
    private IComponentManager componentManager;
    private IComponentProfile activeProfile = null;

    public String[] getRequiredInterfaces() {
        return new String[]{
            IComponentRepositoryManager.class.getName(),
            IComponentProfileRepositoryManager.class.getName()
        };
    }

    public void initialize() {
        super.initialize();
        componentManager = (IComponentManager) getConnectedComponents().
                get(IComponentManager.class.getName());

        repository = (IComponentProfileRepositoryManager) getConnectedComponents().
                get(IComponentProfileRepositoryManager.class.getName());
        activeProfile = repository.getProfileByName("default");
    }

    public void changeProfile(String old, String newProfile) {
        IComponentProfile pOld = getProfileByName(old);
        IComponentProfile pNew = getProfileByName(newProfile);
        changeProfile(pOld, pNew);
    }

    public void changeProfile(IComponentProfile profile, IComponentProfile newProfile) {
        destroyProfile(profile);
        loadAndinitializeProfile(newProfile);
        activeProfile = newProfile;
    }

    public void destroyProfile(String profile) {
        IComponentProfile p = getProfileByName(profile);
        destroyProfile(p);
    }

    public void destroyProfile(IComponentProfile profile) {
        Vector components = profile.getComponentNames();
        String componentName;
        IComponent component;

        for (int i=0; i< components.size(); i++){
            componentName = (String )components.elementAt(i);
            component
                    = componentManager.getComponent(componentName);
            componentManager.destroyComponent(component);

        }
    }

    public IComponentProfile getActiveProfile() {
        return activeProfile;
    }

    public void loadAndinitializeProfile(String profile) {
        IComponentProfile p = getProfileByName(profile);
        loadAndinitializeProfile(p);
    }

    public void loadAndinitializeProfile(IComponentProfile profile) {
        Vector components = profile.getComponentNames();
        String componentName;
        IComponent component;

        for (int i=0; i< components.size(); i++){
            componentName = (String )components.elementAt(i);
            component
                    = componentManager.getComponent(componentName);

        }
    }

    public void pauseProfile(String profile) {
        IComponentProfile p = getProfileByName(profile);
        pauseProfile(p);
        
    }

    public void pauseProfile(IComponentProfile profile) {
        Vector components = profile.getComponentNames();
        String componentName;
        IComponent component;

        for (int i=0; i< components.size(); i++){
            componentName = (String )components.elementAt(i);
            component
                    = componentManager.getComponent(componentName);
            componentManager.pauseComponent(component);

        }
    }

    public void resumeProfile(String profile) {
        IComponentProfile p = getProfileByName(profile);
        resumeProfile(p);
    }

    public void resumeProfile(IComponentProfile profile) {
        Vector components = profile.getComponentNames();
        String componentName;
        IComponent component;

        for (int i=0; i< components.size(); i++){
            componentName = (String )components.elementAt(i);
            component
                    = componentManager.getComponent(componentName);
            componentManager.resumeComponent(component);

        }
    }

    public String getContainerName() {
        return getName();
    }

    public IComponentProfile getProfileByName(String name) {
        return repository.getProfileByName(name);
    }

    public Vector getProfiles() {
        return repository.getProfiles();
    }



}
