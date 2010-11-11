/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.components;

import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.componentmodel.ProxyComponent;

/**
 *
 * @author fenrrir
 */
public class ComponentProfileManager extends ProxyComponent
        implements IComponentProfileManager{

    private IComponentProfileManager concreteComponent;

    public void setConcreteComponent(IComponent concreteComponent){
        super.setConcreteComponent(concreteComponent);
        this.concreteComponent = (IComponentProfileManager) concreteComponent;
    }

    public void resumeProfile(IComponentProfile profile) {
        concreteComponent.resumeProfile(profile);
    }

    public void resumeProfile(String profile) {
        concreteComponent.resumeProfile(profile);
    }

    public void pauseProfile(IComponentProfile profile) {
        concreteComponent.pauseProfile(profile);
    }

    public void pauseProfile(String profile) {
        concreteComponent.pauseProfile(profile);
    }

    public void loadAndinitializeProfile(IComponentProfile profile) {
        concreteComponent.loadAndinitializeProfile(profile);
    }

    public void loadAndinitializeProfile(String profile) {
        concreteComponent.loadAndinitializeProfile(profile);
    }

    public IComponentProfile getActiveProfile() {
        return concreteComponent.getActiveProfile();
    }

    public void destroyProfile(IComponentProfile profile) {
        concreteComponent.destroyProfile(profile);
    }

    public void destroyProfile(String profile) {
        concreteComponent.destroyProfile(profile);
    }

    public void changeProfile(IComponentProfile profile, IComponentProfile newProfile) {
        concreteComponent.changeProfile(profile, newProfile);
    }

    public void changeProfile(String old, String newProfile) {
        concreteComponent.changeProfile(old, newProfile);
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
