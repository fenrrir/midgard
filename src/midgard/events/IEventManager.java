/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.events;

import java.util.Vector;
import midgard.services.IService;

/**
 *
 * @author fenrrir
 */
public interface IEventManager extends IService {
    public Vector listAllEvents();
    public Vector listAllCustomEvents();
    public Vector listInstalledCustomEvents();
    public ICustomEvent getCustomEvent(String name);
    public IEvent getEvent(String name);
    public void loadAndInitializeCustomEvent(String name);
    public void destroyCustomEvent(String name);
    public void destroyCustomEvent(ICustomEvent event);
    public void resumeCustomEvent(String name);
    public void resumeCustomEvent(ICustomEvent event);
    public void pauseCustomEvent(String name);
    public void pauseCustomEvent(ICustomEvent event);
}
