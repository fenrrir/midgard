/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web;

import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.services.ProxyService;

/**
 *
 * @author fenrrir
 */
public class WebServer extends ProxyService implements IWebServer{
    private IWebServer concreteComponent;


    public void setConcreteComponent(IComponent component){
        super.setConcreteComponent(component);
        concreteComponent = (IWebServer) component;
    }

    public void run() {
        concreteComponent.run();
    }

    public void removeWebApplication(IWebApplication application) {
        concreteComponent.removeWebApplication(application);
    }

    public Vector listWebApplications() {
        return concreteComponent.listWebApplications();
    }

    public void addWebApplication(IWebApplication application) {
        concreteComponent.addWebApplication(application);
    }

}
