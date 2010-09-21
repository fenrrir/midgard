/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.kernel;

import midgard.componentmodel.IComponent;
import midgard.componentmodel.IProxyComponent;
import midgard.components.ComponentManager;
import midgard.components.IComponentManager;
import midgard.naming.INameService;
import midgard.repositories.ComponentRepositoryManager;
import midgard.repositories.IComponentRepositoryManager;

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

        
        repositoryManager = (IComponentRepositoryManager)
           repositoryProxy.getImplementationOfInterface("IComponentRepositoryManager");
        
        if ( !repositoryProxy.getConcreteComponent().getName().equals(repositoryManager.getName()) ){
            repositoryProxy.pause();
            repositoryManager.destroy();
            repositoryProxy.setConcreteComponent(repositoryManager);
            repositoryProxy.load();
            repositoryProxy.initialize();
        }


        componentManager = (ComponentManager) 
                        repositoryManager.getProxyOf("IComponentManager");

        componentManagerImpl = (IComponentManager)
                repositoryManager.getImplementationOfInterface("IComponentManager");


        componentManager.setConcreteComponent(componentManagerImpl);
        componentManager.load();
        componentManager.connect("IComponentRepositoryManager", repositoryProxy);
        componentManager.initialize();

    }

    public INameService getNameService(){
        return (INameService) componentManager.resolveComponent("INameService");
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
