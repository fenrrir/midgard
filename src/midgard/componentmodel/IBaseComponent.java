/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.componentmodel;

/**
 *
 * @author fenrrir
 */
public interface IBaseComponent {
    void connect(String interfaceName, IComponent component);
    void disconnect(String interfaceName, IComponent component);
    String [] getRequiredInterfaces();
    String [] getProvidedInterfaces();

}
