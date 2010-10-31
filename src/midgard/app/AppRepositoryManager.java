/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.app;

import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.repositories.RepositoryManager;

/**
 *
 * @author fenrrir
 */
public class AppRepositoryManager extends RepositoryManager implements IAppRepositoryManager{
    private IAppRepositoryManager concreteComponent;
    
    
    public void setConcreteComponent(IComponent concreteComponent){
        super.setConcreteComponent(concreteComponent);
        this.concreteComponent = (IAppRepositoryManager) concreteComponent;
    }

    public IThreadedApp getThreadedApp(String name) {
        return concreteComponent.getThreadedApp(name);
    }

    public Vector getInstalledAppNames() {
        return concreteComponent.getInstalledAppNames();
    }

    public IApp getApp(String name) {
        return concreteComponent.getApp(name);
    }

    public Vector listServices() {
        return concreteComponent.listServices();
    }

    public Vector listSensors() {
        return concreteComponent.listSensors();
    }

        
}
