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

package midgard.kernel;

import midgard.components.ComponentManager;
import midgard.components.IComponentManager;
import midgard.naming.DNS;
import midgard.naming.INameService;
import midgard.components.ComponentRepositoryManager;
import midgard.components.IComponentRepositoryManager;
import midgard.sensors.ISensorManager;
import midgard.services.IService;

/**
 *
 * @author fenrrir
 */
public class MicroKernel {

    private static MicroKernel instance = null;
    private ComponentManager componentManager;

    private MicroKernel(){
        boot();
    }


    private void boot(){

        
        IComponentRepositoryManager repositoryManager;
        ComponentRepositoryManager repositoryProxy;

        IComponentManager componentManagerImpl;


        repositoryProxy = ComponentRepositoryManager.getDefault();

        repositoryProxy.load();
        repositoryProxy.initialize();
        repositoryProxy.open();

        
        repositoryManager = (IComponentRepositoryManager)
           repositoryProxy.getImplementationOfInterface(DNS.ICOMPONENTREPOSITORYMANAGER);
        
        if ( !repositoryProxy.getConcreteComponent().getName().equals(repositoryManager.getName()) ){
            repositoryProxy.pause();
            repositoryManager.destroy();
            repositoryProxy.setConcreteComponent(repositoryManager);
            repositoryProxy.load();
            repositoryProxy.initialize();
        }


        componentManager = (ComponentManager) 
                        repositoryManager.getProxyOf(DNS.ICOMPONENTMANAGER);

        componentManagerImpl = (IComponentManager)
                repositoryManager.getImplementationOfInterface(DNS.ICOMPONENTMANAGER);


        componentManager.setConcreteComponent(componentManagerImpl);
        componentManager.load();
        componentManager.connect(DNS.ICOMPONENTREPOSITORYMANAGER, repositoryProxy);
        componentManager.initialize();

        IService appManager =  (IService)
                        componentManager.resolveComponent(DNS.IAPPMANAGER);
        appManager.startService();

        

    }

    public INameService getNameService(){
        return (INameService) componentManager.resolveComponent(DNS.INAMESERVICE);
    }

    public ClassLoader getClassLoader(){
        return ClassLoader.getInstance();
    }

    public static MicroKernel getInstance(){
        if (instance == null)
            instance = new MicroKernel();
        return instance;
    }

}
