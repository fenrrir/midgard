package midgard;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import midgard.componentmodel.IComponent;
import midgard.components.ComponentManager;
import midgard.repositories.ComponentRepositoryManager;
import midgard.repositories.DefaultComponentRepositoryManager;


public class Test extends MIDlet {

    protected void startApp() throws MIDletStateChangeException {
        IComponent proxy, concrete;
      
        ComponentRepositoryManager manager = ComponentRepositoryManager.getDefault();
        manager.open();
        proxy = manager.getProxyOf("IComponentManager");
        concrete = manager.getImplementationOfInterface("IComponentManager");
        System.out.println(proxy.getName());
        System.out.println(concrete.getName());
        //System.out.println("Hello World");
            // TODO
      
    }

    protected void pauseApp() {
        // This is not currently called by the Squawk VM
    }

    /**
     * Called if the MIDlet is terminated by the system.
     * I.e. if startApp throws any exception other than MIDletStateChangeException,
     * if the isolate running the MIDlet is killed with Isolate.exit(), or
     * if VM.stopVM() is called.
     * 
     * It is not called if MIDlet.notifyDestroyed() was called.
     *
     * @param unconditional If true when this method is called, the MIDlet must
     *    cleanup and release all resources. If false the MIDlet may throw
     *    MIDletStateChangeException  to indicate it does not want to be destroyed
     *    at this time.
     */
    protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
        // TODO
    }
}
