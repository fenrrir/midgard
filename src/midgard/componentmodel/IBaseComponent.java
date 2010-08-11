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
    void connect(IComponent component);
    void disconnect(IComponent component);
    String [] getRequiredInterfaces();
    String [] getProvidedInterfaces();

}
