/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.componentmodel;

import midgard.events.IEventHandler;
import midgard.events.IListener;

/**
 *
 * @author fenrrir
 */
public interface IComponent extends IBaseComponent, 
                                    ILifeCycle,
                                    IEventHandler,
                                    IListener,
                                    IConfiguration{

}
