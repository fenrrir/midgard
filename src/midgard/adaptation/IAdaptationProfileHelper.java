/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.adaptation;

import midgard.componentmodel.IComponent;

/**
 *
 * @author fenrrir
 */
public interface IAdaptationProfileHelper extends IComponent {
    
    public void startService(String name);
    public void stopService(String name);

    public void loadComponent(String name);
    public void pauseComponent(String name);
    public void resumeComponent(String name);
    public void destroyComponent(String name);
    public void changeComponent(String old, String newc);


    public void loadComponentProfile(String name);
    public void pauseComponentProfile(String name);
    public void resumeComponentProfile(String name);
    public void destroyComponentProfile(String name);
    public void changeComponentProfile(String old, String newc);
    
    
    public void loadTask(String name);
    public void pauseTask(String name);
    public void resumeTask(String name);
    public void destroyTask(String name);
    
    
    public void loadCustomEvent(String name);
    public void pauseCustomEvent(String name);
    public void resumeCustomEvent(String name);
    public void destroyCustomEvent(String name);

    public void profileFireEvent( IAdaptationProfile profile,
                                  String eventType,
                                  Object content);



}
