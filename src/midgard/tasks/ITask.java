/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.tasks;

import midgard.componentmodel.IComponent;

/**
 *
 * @author fenrrir
 */
public interface ITask extends IComponent{

    public void run();
    public boolean onBoot();
    public boolean onEvent();
    public IComponent connectAtComponent();

}
