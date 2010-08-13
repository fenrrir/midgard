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
public class ThemperatureSensor extends ProxyComponent implements IThemperatureSensor {

    public ThemperatureSensor(IThemperatureSensor concreteComponent) {
        super( (IComponent) concreteComponent);
    }


}
