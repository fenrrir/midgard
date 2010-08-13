/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

import midgard.events.ICustomEvent;

/**
 *
 * @author fenrrir
 */
public interface IEventRepositoryManager extends IRepositoryManager {
    public ICustomEvent getEvent(String name);

}
