/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web;

import java.util.Vector;
import midgard.componentmodel.IComponent;

/**
 *
 * @author fenrrir
 */
public interface IWebApplication extends IComponent, IURLHandler {
    public Vector getURIs();
}
