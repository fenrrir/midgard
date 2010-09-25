/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.app;

import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.services.ProxyService;

/**
 *
 * @author fenrrir
 */
public class AppManager extends ProxyService implements IAppManager {
    private IAppManager concreteComponent;

    public AppManager() {
    }

    

    public void setConcreteComponent(IComponent component) {
        super.setConcreteComponent(  component);
        concreteComponent = (IAppManager) component;
    }


    public void resumeApps() {
        concreteComponent.resumeApps();
    }

    public void pauseApps() {
        concreteComponent.pauseApps();
    }

    public void loadAndInitializeApps() {
        concreteComponent.loadAndInitializeApps();
    }

    public IThreadedApp getThreadedApp(String name) {
        return concreteComponent.getThreadedApp(name);
    }

    public Vector getAppNames() {
        return concreteComponent.getAppNames();
    }

    public IApp getApp(String name) {
        return concreteComponent.getApp(name);
    }

    public void destroryApps() {
        concreteComponent.destroryApps();
    }



}