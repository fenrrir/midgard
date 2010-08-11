/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.componentmodel;

/**
 *
 * @author fenrrir
 */
public abstract class ProxyComponent implements IComponent {
    
    private IComponent concreteComponent;

    public ProxyComponent(IComponent concreteComponent) {
        this.concreteComponent = concreteComponent;
    }

    public String[] getRequiredInterfaces() {
        return concreteComponent.getRequiredInterfaces();
    }

    public String[] getProvidedInterfaces() {
        return concreteComponent.getProvidedInterfaces();
    }

    public void disconnect(IComponent component) {
        concreteComponent.disconnect(component);
    }

    public void connect(IComponent component) {
        concreteComponent.connect(component);
    }

    public void resume() {
        concreteComponent.resume();
    }

    public void pause() {
        concreteComponent.pause();
    }

    public void load() {
        concreteComponent.load();
    }

    public void initialize() {
        concreteComponent.initialize();
    }

    public void destroy() {
        concreteComponent.destroy();
    }



}
