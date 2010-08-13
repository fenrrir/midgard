/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

/**
 *
 * @author fenrrir
 */
public class EventRepositoryManager extends RepositoryManager  implements IEventRepositoryManager{

    public EventRepositoryManager(IEventRepositoryManager concreteComponent) {
        super(concreteComponent);
    }
}
