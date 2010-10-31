/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.kernel;

import midgard.app.IAppManager;
import midgard.components.ComponentManager;
import midgard.components.IComponentManager;
import midgard.naming.DNS;
import midgard.naming.INameService;
import midgard.components.ComponentRepositoryManager;
import midgard.components.IComponentRepositoryManager;
import midgard.sensors.ISensorManager;

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

        IAppManager appManager =  (IAppManager)
                        componentManager.resolveComponent(DNS.IAPPMANAGER);
        appManager.loadAndInitializeApps();

        ISensorManager sensorManager = (ISensorManager)
                        componentManager.resolveComponent(ISensorManager.class.getName());
        sensorManager.startService();

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
