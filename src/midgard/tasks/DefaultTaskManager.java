/*
* Copyright (C) 2011 Rodrigo Pinheiro Marques de Araujo
*
* This program is free software; you can redistribute it and/or modify it under
* the terms of the GNU General Public License as published by the Free Software
* Foundation; either version 2 of the License, or (at your option) any later
* version.
*
* This program is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
* FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
* details.
*
* You should have received a copy of the GNU General Public License along with
* this program; if not, write to the Free Software Foundation, Inc., 51
* Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
*/

package midgard.tasks;

import java.util.Vector;
import midgard.app.IAppManager;
import midgard.app.IAppRepositoryManager;
import midgard.componentmodel.IComponent;
import midgard.components.IComponentManager;
import midgard.repositories.ITaskRepositoryManager;
import midgard.services.Service;

/**
 *
 * @author fenrrir
 */
public class DefaultTaskManager extends Service
        implements ITaskManager{

    private IComponentManager componentManager;
    private IAppRepositoryManager appRepository;
    private ITaskRepositoryManager repository;



    public String[] getRequiredInterfaces() {
        return new String [] {
            IComponentManager.class.getName(),
            IAppRepositoryManager.class.getName(),
            ITaskRepositoryManager.class.getName()
        };
    }
    


    public void initialize() {
        super.initialize();
        appRepository = (IAppRepositoryManager) getConnectedComponents()
                .get(IAppRepositoryManager.class.getName());
        componentManager = (IComponentManager) getConnectedComponents()
                .get(IComponentManager.class.getName());
        repository = (ITaskRepositoryManager) getConnectedComponents()
                .get(ITaskRepositoryManager.class.getName());
    }


    public void startService() {
        super.startService();

        Vector tasks = listInstalledTasks();
        String taskName;

        for (int i=0; i < tasks.size(); i++){
            taskName = (String)tasks.elementAt(i);
            loadAndInitializeTask(taskName);
        }
    }

    public void stopService() {
        super.stopService();

        Vector tasks = listInstalledTasks();
        String taskName;

        for (int i=0; i < tasks.size(); i++){
            taskName = (String)tasks.elementAt(i);
            destroyTask(taskName);
        }
    }


    public void destroyTask(String name) {
        ITask task = getTask(name);
        destroyTask((ITask) task);
    }

    public void destroyTask(ITask task) {
        Vector components;
        IComponent component;

        if (task.onEvent()){
            components = task.connectAtComponents();
            for (int i=0; i<components.size(); i++){
                component = componentManager
                        .resolveComponent((String) components.elementAt(i)  );
                component.removeEventListener(task);
            }
        }

        componentManager.destroyComponent((IComponent) task);
    }


    public Vector listAllTasks() {
        return repository.list();
    }

    public Vector listInstalledTasks() {
        return appRepository.listTasks();
    }

    public void loadAndInitializeTask(String name) {
        ITask task = getTask(name);
        
        Vector components;
        IComponent component;

        if (task.onEvent()){
            components = task.connectAtComponents();
            for (int i=0; i<components.size(); i++){
                component = componentManager
                        .resolveComponent((String) components.elementAt(i)  );
                component.registerEventListener(task);
            }
        }
    }

    public void pauseTask(String name) {
        ITask task = getTask(name);
        pauseTask((ITask) task);
    }

    public void pauseTask(ITask task) {
        componentManager.pauseComponent((ITask) task);
    }

    public void resumeTask(String name) {
        ITask task = getTask(name);
        resumeTask((ITask) task);
    }

    public void resumeTask(ITask task) {
        componentManager.resumeComponent((ITask) task);
    }

    public ITask getTask(String name) {
        return (ITask) componentManager.resolveComponent(name);
    }



}
