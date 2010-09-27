/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors;

/**
 *
 * @author fenrrir
 */
public class TemperatureSensorData {
    private double celsius, fahrenheit;

    public TemperatureSensorData(double celsius, double fahrenheit) {
        this.celsius = celsius;
        this.fahrenheit = fahrenheit;
    }


    public double getCelsius() {
        return celsius;
    }

    public double getFahrenheit() {
        return fahrenheit;
    }



}
