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
public class ThresholdChangedTemperatureData{

    public double low, high;

    public ThresholdChangedTemperatureData(double low, double high) {
        this.low = low;
        this.high = high;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    

}
