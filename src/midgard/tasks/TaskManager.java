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
