/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.pubsubhubbub;

import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.services.ProxyService;
import midgard.web.Request;
import midgard.web.Response;

/**
 *
 * @author fenrrir
 */
public class Hub extends ProxyService implements IHub {
    private IHub concreteComponent;

    public void setConcreteComponent(IComponent component){
        super.setConcreteComponent(component);
        this.concreteComponent = (IHub) concreteComponent;

    }

    public Vector getURIs() {
        return concreteComponent.getURIs();
    }

    public Response serve(Request request) throws Exception {
        return concreteComponent.serve(request);
    }

}
