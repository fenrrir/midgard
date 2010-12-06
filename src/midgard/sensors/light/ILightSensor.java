/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors.light;

import midgard.sensors.ISensor;

/**
 *
 * @author fenrrir
 */
public interface ILightSensor extends ISensor{
    public void enableThresholds(int low, int high);
    public void disableThresholds();

}
