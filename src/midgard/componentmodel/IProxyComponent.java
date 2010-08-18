/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.componentmodel;

/**
 *
 * @author fenrrir
 */
public interface IProxyComponent extends IComponent {
    public IComponent getConcreteComponent();
    public void setConcreteComponent(IComponent component);

}
