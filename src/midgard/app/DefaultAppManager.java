/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midgard.app;

import java.util.Vector;
import midgard.components.IComponentManager;
import midgard.sensors.ISensorManager;
import midgard.services.IService;
import midgard.services.Service;

/**
 *
 * @author fenrrir
 */
public class DefaultAppManager extends Service implements IAppManager {

    private IAppRepositoryManager repository = null;
    private IComponentManager componentManager = null;

     public String[] getRequiredInterfaces() {
        return new String[]{
            IAppRepositoryManager.class.getName(),
            IComponentManager.class.getName()
        };
    }

        public void initialize() {
        super.initialize();
        repository = (IAppRepositoryManager) getConnectedComponents()
                .get(IAppRepositoryManager.class.getName());


        componentManager = (IComponentManager) getConnectedComponents()
                .get(IComponentManager.class.getName());

        repository.open();
    }

    public void destroryApps() {

        IApp app;
        Vector names = repository.getInstalledAppNames();

        for (int i = 0; i < names.size(); i++) {
            app = repository.getApp((String) names.elementAt(i));
            app.destroy();

        }
    }

    public void loadAndInitializeApps() {
        IApp app;
        Thread thread;

        Vector names = repository.getInstalledAppNames();

        for (int i = 0; i < names.size(); i++) {
            app = repository.getApp((String) names.elementAt(i));
            if (app instanceof ThreadedApp){
                thread = new Thread( (Runnable) app);
                thread.start();
            }
        }
    }

    public void pauseApps() {
        IApp app;

        Vector names = repository.getInstalledAppNames();

        for (int i = 0; i < names.size(); i++) {
            app = repository.getApp((String) names.elementAt(i));
            app.pause();

        }
    }

    public void resumeApps() {
        IApp app;

        Vector names = repository.getInstalledAppNames();

        for (int i = 0; i < names.size(); i++) {
            app = repository.getApp((String) names.elementAt(i));
            app.resume();

        }
    }

    public void startService() {
        super.startService();

        if( repository.getInstalledAppNames().size() > 0 ){
            loadAndInitializeApps();


            

            Vector services = repository.listServices();
            String serviceName;
            IService service;

            for (int i=0; i< services.size(); i++){
                serviceName = (String) services.elementAt(i);
                service = (IService) componentManager.resolveComponent(serviceName);
                service.startService();

            }

            ISensorManager sensorManager = (ISensorManager)
                        componentManager.resolveComponent(ISensorManager.class.getName());
            sensorManager.startService();

        }

        
    }


}
