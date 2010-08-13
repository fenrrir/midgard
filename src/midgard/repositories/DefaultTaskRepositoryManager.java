/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

import java.util.Vector;
import midgard.componentmodel.Component;
import midgard.componentmodel.IComponent;
import midgard.tasks.ITask;

/**
 *
 * @author fenrrir
 */
public class DefaultTaskRepositoryManager extends Component implements ITaskRepositoryManager {

    public void clear() {
    }

    public void close() {
    }

    public void open() {
    }

    public IComponent get(String name) {
        return null;
    }

    public ITask getTask(String name) {
        return (ITask) get(name);
    }

    public Vector list() {
        return null;
    }

}
