/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.events;
import java.util.Vector;
/**
 *
 * @author fenrrir
 */
public interface IEventHandler {
    void registerEventListener(IListener listener);
    void removeEventListener(IListener listener);
    void fireEvent(IEvent event);
    Vector getEventHistory(IEvent event);


}