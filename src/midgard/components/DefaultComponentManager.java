/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.components;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.componentmodel.IProxyComponent;
import midgard.repositories.IComponentRepositoryManager;
import midgard.services.Service;

/**
 *
 * @author fenrrir
 */
public class DefaultComponentManager extends Service implements IComponentManager{

    private IComponentRepositoryManager repository = null;
    private Hashtable requestComponent; // for loop safe

    public void initialize() {
        super.initialize();
        requestComponent = new Hashtable();
        repository = (IComponentRepositoryManager)
                getConnectedComponents().get("IComponentRepositoryManager");
    }


    private void setRequestComponent(String name){
        requestComponent.put(Thread.currentThread(), name);
    }

    private String getRequestComponent(){
        if (requestComponent.contains(Thread.currentThread()))
            return (String) requestComponent.get(Thread.currentThread());
        else
            return "";
    }

    public void destroy() {
        super.destroy();
        requestComponent.clear();

    }

    public void pause() {
        super.pause();
    }




    public String[] getRequiredInterfaces(){
        return new String [] {"IComponentRepositoryManager"};
    }

    public void changeImplementation(IProxyComponent proxy, IComponent old, IComponent comp) {
        changeImplementation(proxy, comp);
    }

    public void changeImplementation(IProxyComponent proxy, IComponent comp) {
        proxy.pause();
        proxy.destroy();
        proxy.setConcreteComponent(comp);
    }

    public void destroyComponent(IComponent component) {
        component.destroy();
    }

    public void initializeComponent(IComponent component) {
        if (!component.isInitialized())
            component.initialize();
    }

    public void loadComponent(IComponent component) {
        if (!component.isLoaded())
            component.load();
    }

    private void loadAndInitializeComponent(IComponent component){
        String [] requires;
        IComponent dependency = null;

        if (getRequestComponent().equals("")) // for loop detection
            setRequestComponent(component.getName());

        loadComponent(component);

        requires = component.getRequiredInterfaces();

        for(int i=0; i< requires.length; i++){
            String name = requires[i];

            if (name.equals(getRequestComponent())){
                // loop
                dependency = getComponent(name, false);
            }else{
                dependency = resolveComponent(name);
            }
            component.connect(name, dependency);
        }

        initializeComponent(component);

        if (getRequestComponent().equals(component.getName())){ // configuration ended
            setRequestComponent("");
        }
    }

    public void pauseComponent(IComponent component) {
        if (!component.isPaused())
            component.pause();
    }

    public void resumeComponent(IComponent component) {
        if (component.isPaused())
            component.resume();
    }

    public Vector getInterfaceNames() {
        return repository.getInterfaceNames();
    }

    public IComponent getImplementationOfInterface(String name) {
        return repository.getImplementationOfInterface(name);
    }

    public Vector getComponentsFromInterface(String name) {
        return repository.getComponentsFromInterface(name);
    }

    public Vector getComponentNames() {
        return repository.getComponentNames();
    }

    private IComponent getComponent(String name, boolean initialize){
        IComponent component = repository.getComponent(name);
        if (initialize)
            loadAndInitializeComponent(component);
        return component;
    }

    public IComponent getComponent(String name) {
        return getComponent(name, true);
    }

    public IComponent resolveComponent(String name) {
        IComponent component;
        if (componentIsInterface(name)){
            IProxyComponent proxy = repository.getProxyOf(name);
            loadAndInitializeComponent(proxy);
            component = getComponent(name);
            proxy.setConcreteComponent(component);
            return proxy;

        }
        return getComponent(name);
    }



    private boolean componentIsInterface(String name){
        if (name.startsWith("I"))
            return true;
        return false;
    }

    


}
