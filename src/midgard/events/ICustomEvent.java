/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.events;

import java.util.Vector;
import midgard.componentmodel.IComponent;

/**
 *
 * @author fenrrir
 */
public interface ICustomEvent extends IEvent, IComponent{
    public boolean onBoot();
    public Vector connectIn();

}
