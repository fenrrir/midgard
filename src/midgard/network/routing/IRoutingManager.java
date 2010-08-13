/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.network.routing;

import midgard.services.IService;

/**
 *
 * @author fenrrir
 */
public interface IRoutingManager extends IService {
    IRouting getRouting();
}
