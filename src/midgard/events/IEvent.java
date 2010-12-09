/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.events;

/**
 *
 * @author fenrrir
 */
public interface IEvent {
    long getType();
    long getID();
    Object getContentObject();
    public void setContentObject(Object content);

}
