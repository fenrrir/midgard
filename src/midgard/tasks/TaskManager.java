/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.tasks;

import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.services.ProxyService;

/**
 *
 * @author fenrrir
 */
public class TaskManager extends ProxyService implements ITaskManager{
    private ITaskManager concreteComponent;


    public void setConcreteComponent(IComponent concreteComponent){
        super.setConcreteComponent(concreteComponent);
        this.concreteComponent = (ITaskManager) concreteComponent;
    }

    public void resumeTask(ITask task) {
        concreteComponent.resumeTask(task);
    }

    public void resumeTask(String name) {
        concreteComponent.resumeTask(name);
    }

    public void pauseTask(ITask task) {
        concreteComponent.pauseTask(task);
    }

    public void pauseTask(String name) {
        concreteComponent.pauseTask(name);
    }

    public void loadAndInitializeTask(String name) {
        concreteComponent.loadAndInitializeTask(name);
    }

    public Vector listInstalledTasks() {
        return concreteComponent.listInstalledTasks();
    }

    public Vector listAllTasks() {
        return concreteComponent.listAllTasks();
    }

    public void destroyTask(ITask task) {
        concreteComponent.destroyTask(task);
    }

    public void destroyTask(String name) {
        concreteComponent.destroyTask(name);
    }

    public ITask getTask(String name) {
        return concreteComponent.getTask(name);
    }




}
