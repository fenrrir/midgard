/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors;

import midgard.componentmodel.IComponent;
import midgard.componentmodel.ProxyComponent;

/**
 *
 * @author fenrrir
 */
public class TemperatureSensor extends ProxyComponent implements ITemperatureSensor {

    public TemperatureSensor(ITemperatureSensor concreteComponent) {
        super( (IComponent) concreteComponent);
    }


}