/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

    public void handleRequest(InputStream ins, OutputStream outs) throws IOException {
        concreteComponent.handleRequest(ins, outs);
    }

    public void addView(URLView view) {
        concreteComponent.addView(view);
    }

}
