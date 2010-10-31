/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors.temperature;

import midgard.sensors.ThresholdChangedData;

/**
 *
 * @author fenrrir
 */
public class ThresholdChangedTemperatureData extends ThresholdChangedData{

    public ThresholdChangedTemperatureData(int low, int high) {
        super(low, high);
    }

}
