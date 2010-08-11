/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.componentmodel;

import java.util.Hashtable;
import java.util.Vector;

import midgard.events.IEvent;
import midgard.events.IListener;

/**
 *
 * @author fenrrir
 */
public abstract class Component implements IComponent {
    private Hashtable components, events;
    private Vector listeners;
    private String[] requiredInterfaces = {};
    private String[] providedInterfaces = {};

    public Component() {
        components = new Hashtable();
        events = new Hashtable();
        listeners = new Vector();
    }

    public void connect(String interfaceName, IComponent component) {
        Vector v;
        if (components.containsKey(interfaceName)){
            v = (Vector) components.get(interfaceName);
            v.addElement(component);
        }else{
            v = new Vector();
            v.addElement(component);
            components.put(interfaceName, v);
        }
    }

    public void disconnect(String interfaceName,  IComponent component) {
        if (components.containsKey(interfaceName)){
            Vector v = (Vector) components.get(interfaceName);
            v.removeElement(component);
        }
    }

    public String[] getProvidedInterfaces() {
        return providedInterfaces;
    }

    public String[] getRequiredInterfaces() {
        return requiredInterfaces;
    }

    public Vector getEventHistory(IEvent event) {
        if (events.containsKey(new Float(event.getType()))){
            return (Vector) events.get(new Float ( event.getType()));
        }
        return new Vector();
   
    }

    public void registerEventListener(IListener listener) {
        listeners.addElement(listener);
    }

    public void removeEventListener(IListener listener) {
        listeners.removeElement(listener);
    }

    public void fireEvent(IEvent event) {
        Vector v;
        if (events.containsKey(new Float(event.getType()))){
            v = (Vector) events.get(event);
            if (events.size()  < 5){
                v.addElement(event);
            }
            
        }else{
            v = new Vector();
            events.put(new Float(event.getType()), v);
            v.addElement(event);

        }


        IListener listener;
        for (int i=0; i < listeners.size(); i++){
            listener = (IListener) listeners.elementAt(i);
            listener.newEventArrived(event);
        }
    }



}
