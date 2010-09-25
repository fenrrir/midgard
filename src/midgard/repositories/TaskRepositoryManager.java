/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

import midgard.componentmodel.IComponent;
import midgard.tasks.ITask;

/**
 *
 * @author fenrrir
 */
public class TaskRepositoryManager extends RepositoryManager  implements ITaskRepositoryManager{
    private ITaskRepositoryManager concreteComponent;

    

    public void setConcreteComponent(IComponent concreteComponent){
        super.setConcreteComponent(concreteComponent);
        this.concreteComponent = (ITaskRepositoryManager) concreteComponent;
    }

    public ITask getTask(String name){
        return this.concreteComponent.getTask(name);
    }
}
