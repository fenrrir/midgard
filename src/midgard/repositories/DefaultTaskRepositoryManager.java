/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

import java.util.Vector;
import midgard.componentmodel.Component;
import midgard.componentmodel.IComponent;
import midgard.components.IComponentManager;
import midgard.components.IComponentRepositoryManager;
import midgard.tasks.ITask;

/**
 *
 * @author fenrrir
 */
public class DefaultTaskRepositoryManager extends Component implements ITaskRepositoryManager {

    private IComponentRepositoryManager repository;
    private IComponentManager componentManager;

    public void initialize() {
        super.initialize();
        repository = (IComponentRepositoryManager) 
                getConnectedComponents()
                    .get(IComponentRepositoryManager.class.getName());

        componentManager = (IComponentManager)
                getConnectedComponents()
                    .get(IComponentManager.class.getName());
    }

    public String[] getRequiredInterfaces() {
        return new String[]{
            IComponentRepositoryManager.class.getName(),
            IComponentManager.class.getName()

        };
    }



    public void clear() {
    }

    public void close() {
    }

    public void open() {
    }

    public IComponent get(String name) {
        return componentManager.resolveComponent(name);
    }

    public ITask getTask(String name) {
        return (ITask) get(name);
    }

    public Vector list() {
        return repository.getComponentsFromInterface(ITask.class.getName());
    }

}
