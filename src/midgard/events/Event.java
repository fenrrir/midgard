/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.events;

/**
 *
 * @author fenrrir
 */
public class Event implements IEvent{
    private Object content;

    public Event(Object content) {
        this.content = content;
    }



    public Object getContentObject() {
        return this.content;
    }

    public long getID() {
        return this.hashCode();
    }

    public long getType() {
        return this.getClass().hashCode();
    }

    public void setContentObject(Object content) {
        this.content = content;
    }


}
