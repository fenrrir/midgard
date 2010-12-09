/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.adaptation;

import java.util.Vector;
import midgard.app.IAppRepositoryManager;
import midgard.services.Service;

/**
 *
 * @author fenrrir
 */
public class DefaultAdaptationManager extends Service
        implements IAdaptationManager {

    private IAdaptationProfileManager adaptationProfileManager;
    private IAppRepositoryManager appRepository;

    public String[] getRequiredInterfaces() {
        return new String [] {
            IAdaptationProfileManager.class.getName(),
            IAppRepositoryManager.class.getName()
        };
    }

    public void initialize() {
        super.initialize();
        appRepository = (IAppRepositoryManager) getConnectedComponents()
                .get(IAppRepositoryManager.class.getName());
        adaptationProfileManager = (IAdaptationProfileManager) getConnectedComponents()
                .get(IAdaptationProfileManager.class.getName());
    }


    public void startService() {
        String profileName;
        Vector adaptationProfiles = appRepository.listAdaptationProfiles();
        for (int i=0; i < adaptationProfiles.size(); i++){
            profileName = (String) adaptationProfiles.elementAt(i);
            adaptationProfileManager.loadProfile(profileName);
        }

    }

    public void stopService() {
        String profileName;
        Vector adaptationProfiles = appRepository.listAdaptationProfiles();
        for (int i=0; i < adaptationProfiles.size(); i++){
            profileName = (String) adaptationProfiles.elementAt(i);
            adaptationProfileManager.unloadProfile(profileName);
        }

    }



    public void stopService(String name) {
        adaptationProfileManager.stopService(name);
    }

    public void startService(String name) {
        adaptationProfileManager.startService(name);
    }

    public void resumeTask(String name) {
        adaptationProfileManager.resumeTask(name);
    }

    public void resumeCustomEvent(String name) {
        adaptationProfileManager.resumeCustomEvent(name);
    }

    public void resumeComponentProfile(String name) {
        adaptationProfileManager.resumeComponentProfile(name);
    }

    public void resumeComponent(String name) {
        adaptationProfileManager.resumeComponent(name);
    }

    public void profileFireEvent(IAdaptationProfile profile, String eventType, Object content) {
        adaptationProfileManager.profileFireEvent(profile, eventType, content);
    }

    public void pauseTask(String name) {
        adaptationProfileManager.pauseTask(name);
    }

    public void pauseCustomEvent(String name) {
        adaptationProfileManager.pauseCustomEvent(name);
    }

    public void pauseComponentProfile(String name) {
        adaptationProfileManager.pauseComponentProfile(name);
    }

    public void pauseComponent(String name) {
        adaptationProfileManager.pauseComponent(name);
    }

    public void loadTask(String name) {
        adaptationProfileManager.loadTask(name);
    }

    public void loadCustomEvent(String name) {
        adaptationProfileManager.loadCustomEvent(name);
    }

    public void loadComponentProfile(String name) {
        adaptationProfileManager.loadComponentProfile(name);
    }

    public void loadComponent(String name) {
        adaptationProfileManager.loadComponent(name);
    }

    public void destroyTask(String name) {
        adaptationProfileManager.destroyTask(name);
    }

    public void destroyCustomEvent(String name) {
        adaptationProfileManager.destroyCustomEvent(name);
    }

    public void destroyComponentProfile(String name) {
        adaptationProfileManager.destroyComponentProfile(name);
    }

    public void destroyComponent(String name) {
        adaptationProfileManager.destroyComponent(name);
    }

    public void changeComponentProfile(String old, String newc) {
        adaptationProfileManager.changeComponentProfile(old, newc);
    }

    public void changeComponent(String old, String newc) {
        adaptationProfileManager.changeComponent(old, newc);
    }

    public void unloadProfile(String name) {
        adaptationProfileManager.unloadProfile(name);
    }

    public void loadProfile(String name) {
        adaptationProfileManager.loadProfile(name);
    }

    public Vector listProfiles() {
        return adaptationProfileManager.listProfiles();
    }

    public IAdaptationProfile getProfile(String name) {
        return adaptationProfileManager.getProfile(name);
    }



}
