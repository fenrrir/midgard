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
public interface ITaskRepositoryManager extends IRepositoryManager{
    public ITask getTask(String name);
}
