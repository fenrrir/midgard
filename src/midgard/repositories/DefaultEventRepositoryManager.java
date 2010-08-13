/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.repositories;

import java.util.Vector;
import midgard.componentmodel.Component;
import midgard.componentmodel.IComponent;
import midgard.events.ICustomEvent;

/**
 *
 * @author fenrrir
 */
public class DefaultEventRepositoryManager extends Component implements IEventRepositoryManager {

    public void clear() {
    }

    public void close() {
    }

    public void open() {
    }

    public IComponent get(String name) {
        return null;
    }

    public ICustomEvent getEvent(String name){
        return (ICustomEvent) get(name);
    }

    public Vector list() {
        return null;
    }


}
