/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.componentmodel;

import java.util.Hashtable;

/**
 *
 * @author fenrrir
 */
public interface IBaseComponent {
    void connect(String interfaceName, IComponent component);
    void disconnect(String interfaceName, IComponent component);
    Hashtable getConnectedComponents();
    String [] getRequiredInterfaces();
    String [] getProvidedInterfaces();

}
