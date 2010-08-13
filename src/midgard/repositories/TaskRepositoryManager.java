/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

import midgard.tasks.ITask;

/**
 *
 * @author fenrrir
 */
public class TaskRepositoryManager extends RepositoryManager  implements ITaskRepositoryManager{
    private ITaskRepositoryManager concreteComponent;

    public TaskRepositoryManager(ITaskRepositoryManager concreteComponent) {
        super(concreteComponent);
    }

    public ITask getTask(String name){
        return this.concreteComponent.getTask(name);
    }
}
