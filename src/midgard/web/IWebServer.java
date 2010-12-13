/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web;

import java.util.Vector;
import midgard.services.IService;

/**
 *
 * @author fenrrir
 */
public interface IWebServer extends IService, Runnable{
    public void addWebApplication(IWebApplication application);
    public void removeWebApplication(IWebApplication application);
    public Vector listWebApplications();
}
