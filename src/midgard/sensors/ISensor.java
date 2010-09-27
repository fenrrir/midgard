/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors;

import midgard.componentmodel.IComponent;

/**
 *
 * @author fenrrir
 */
public interface ISensor extends IComponent{
    public void initSensor();
    public void disableSensor();
    public void collect();

}
