/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.adaptation;

import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.componentmodel.ProxyComponent;

/**
 *
 * @author fenrrir
 */
public class AdaptationProfileManager extends ProxyComponent
        implements IAdaptationProfileManager {


    private IAdaptationProfileManager concreteComponent;

    public void setConcreteComponent(IComponent concreteComponent){
        super.setConcreteComponent(concreteComponent);
        this.concreteComponent = (IAdaptationProfileManager) concreteComponent;
    }

    public void stopService(String name) {
        concreteComponent.stopService(name);
    }

    public void startService(String name) {
        concreteComponent.startService(name);
    }

    public void resumeTask(String name) {
        concreteComponent.resumeTask(name);
    }

    public void resumeCustomEvent(String name) {
        concreteComponent.resumeCustomEvent(name);
    }

    public void resumeComponentProfile(String name) {
        concreteComponent.resumeComponentProfile(name);
    }

    public void resumeComponent(String name) {
        concreteComponent.resumeComponent(name);
    }

    public void profileFireEvent(IAdaptationProfile profile, String eventType, Object content) {
        concreteComponent.profileFireEvent(profile, eventType, content);
    }

    public void pauseTask(String name) {
        concreteComponent.pauseTask(name);
    }

    public void pauseCustomEvent(String name) {
        concreteComponent.pauseCustomEvent(name);
    }

    public void pauseComponentProfile(String name) {
        concreteComponent.pauseComponentProfile(name);
    }

    public void pauseComponent(String name) {
        concreteComponent.pauseComponent(name);
    }

    public void loadTask(String name) {
        concreteComponent.loadTask(name);
    }

    public void loadCustomEvent(String name) {
        concreteComponent.loadCustomEvent(name);
    }

    public void loadComponentProfile(String name) {
        concreteComponent.loadComponentProfile(name);
    }

    public void loadComponent(String name) {
        concreteComponent.loadComponent(name);
    }

    public void destroyTask(String name) {
        concreteComponent.destroyTask(name);
    }

    public void destroyCustomEvent(String name) {
        concreteComponent.destroyCustomEvent(name);
    }

    public void destroyComponentProfile(String name) {
        concreteComponent.destroyComponentProfile(name);
    }

    public void destroyComponent(String name) {
        concreteComponent.destroyComponent(name);
    }

    public void changeComponentProfile(String old, String newc) {
        concreteComponent.changeComponentProfile(old, newc);
    }

    public void changeComponent(String old, String newc) {
        concreteComponent.changeComponent(old, newc);
    }

    public void unloadProfile(String name) {
        concreteComponent.unloadProfile(name);
    }

    public void loadProfile(String name) {
        concreteComponent.loadProfile(name);
    }

    public Vector listProfiles() {
        return concreteComponent.listProfiles();
    }

    public IAdaptationProfile getProfile(String name) {
        return concreteComponent.getProfile(name);
    }



}
