/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.tasks;

import java.util.Vector;
import midgard.componentmodel.IComponent;

/**
 *
 * @author fenrrir
 */
public interface ITask extends IComponent, Runnable{
    public boolean onEvent();
    public Vector connectAtComponents();

}
