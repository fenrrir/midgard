/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web;

import midgard.services.IService;

/**
 *
 * @author fenrrir
 */
public interface IWebServer extends IService, Runnable{
    public void addWebComponent(IWebComponent component);
}
