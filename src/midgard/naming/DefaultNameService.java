/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.naming;

import midgard.componentmodel.IComponent;
import midgard.components.IComponentManager;
import midgard.services.Service;

/**
 *
 * @author fenrrir
 */
public class DefaultNameService extends Service implements INameService {

    private IComponentManager componentManager = null;

    public String[] getRequiredInterfaces(){
        return new String [] {IComponentManager.class.getName()};
    }

    public void initialize() {
        super.initialize();
        componentManager = (IComponentManager)
                getConnectedComponents().get(IComponentManager.class.getName());
    }

    public IComponent resolveName(String name) {
        return componentManager.resolveComponent(name);
    }

}
