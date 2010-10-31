/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web;

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

    public void addWebComponent(IWebComponent component) {
        concreteComponent.addWebComponent(component);
    }

    

}
