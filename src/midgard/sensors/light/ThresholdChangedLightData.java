/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors.light;

import midgard.sensors.ThresholdChangedData;

/**
 *
 * @author fenrrir
 */
public class ThresholdChangedLightData extends ThresholdChangedData{

    public ThresholdChangedLightData(int low, int high) {
        super(low, high);
    }

}
