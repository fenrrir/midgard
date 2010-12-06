/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.tasks;

import java.util.Vector;
import midgard.services.IService;

/**
 *
 * @author fenrrir
 */
public interface ITaskManager extends IService{
    public Vector listAllTasks();
    public Vector listInstalledTasks();
    public ITask getTask(String name);
    public void loadAndInitializeTask(String name);
    public void destroyTask(String name);
    public void destroyTask(ITask task);
    public void resumeTask(String name);
    public void resumeTask(ITask task);
    public void pauseTask(String name);
    public void pauseTask(ITask task);

}
