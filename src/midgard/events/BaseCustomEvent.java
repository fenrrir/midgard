/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.events;

import midgard.componentmodel.Component;

/**
 *
 * @author fenrrir
 */
public abstract class BaseCustomEvent extends Component implements ICustomEvent{

    public long getID() {
        return this.hashCode();
    }

    public long getType() {
        return this.getClass().hashCode();
    }

    public void fireEvent() {
        super.fireEvent(this);
    }



}
