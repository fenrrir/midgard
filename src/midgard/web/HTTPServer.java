/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web;

import java.io.IOException;
import javax.microedition.io.Datagram;
import midgard.componentmodel.IComponent;
import midgard.componentmodel.ProxyComponent;

/**
 *
 * @author fenrrir
 */
public class HTTPServer extends ProxyComponent implements IHTTPServer{
    private IHTTPServer concreteComponent;

    public void setConcreteComponent(IComponent component){
        super.setConcreteComponent(component);
        concreteComponent = (IHTTPServer) component;
    }

    public void handleRequest(Datagram input, Datagram output) throws IOException {
        concreteComponent.handleRequest(input, output);
    }

    public void addView(URLView view) {
        concreteComponent.addView(view);
    }

}
