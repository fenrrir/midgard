/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.naming;

import midgard.componentmodel.IComponent;
import midgard.services.IService;

/**
 *
 * @author fenrrir
 */
public interface INameService extends IService{
    public IComponent resolveName(String name);

}
