/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors;

/**
 *
 * @author fenrrir
 */
public class ThresholdChangedData {
    public int low, high;

    public ThresholdChangedData(int low, int high) {
        this.low = low;
        this.high = high;
    }
}
