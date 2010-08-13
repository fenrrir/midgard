/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.components;

import java.util.Vector;
import midgard.componentmodel.IComponent;

/**
 *
 * @author fenrrir
 */
public interface IComponentContainer {
    public Vector getComponentNames();
    public Vector getInterfaceNames();
    public Vector getComponentsFromInterface();
    public IComponent getImplementationOfInterface(String name);
    public IComponent getComponent(String name);

}
