/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

/**
 *
 * @author fenrrir
 */
public class TaskRepositoryManager extends RepositoryManager  implements ITaskRepositoryManager{

    public TaskRepositoryManager(ITaskRepositoryManager concreteComponent) {
        super(concreteComponent);
    }
}
