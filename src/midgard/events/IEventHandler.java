/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.events;
import java.util.Hashtable;
import java.util.Vector;
/**
 *
 * @author fenrrir
 */
public interface IEventHandler {
    void registerEventListener(IListener listener);
    void removeEventListener(IListener listener);
    Vector getListeners(); // internal data for swap components
    Hashtable getCacheFiredEvents(); // internal data for swap components
    void fireEvent(IEvent event);
    Vector getEventHistory(IEvent event);


}
