/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors.temperature;

import midgard.sensors.ISensor;

/**
 *
 * @author fenrrir
 */
public interface ITemperatureSensor extends ISensor{
    public void enableThresholds(double low, double high, boolean celsius);
    public void disableThresholds();

}
