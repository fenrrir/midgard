/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web;

import midgard.componentmodel.ProxyComponent;

/**
 *
 * @author fenrrir
 */
public class WebServer extends ProxyComponent implements IWebServer{
    private IWebServer concreteComponent;


    public void setConcreteComponent(IWebServer component){
        super.setConcreteComponent(component);
        concreteComponent = component;
    }

    public void run() {
        concreteComponent.run();
    }

    public void addWebComponent(IWebComponent component) {
        concreteComponent.addWebComponent(component);
    }

    

}
