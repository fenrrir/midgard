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
public class NetworkSensor extends ProxyComponent implements INetworkSensor{

    public NetworkSensor(INetworkSensor concreteComponent) {
        super(concreteComponent);
    }


}
