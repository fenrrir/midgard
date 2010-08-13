/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors;

import midgard.battery.IBatteryManager;
import midgard.componentmodel.IComponent;
import midgard.componentmodel.ProxyComponent;

/**
 *
 * @author fenrrir
 */
public class BatterySensor extends ProxyComponent implements IBatterySensor{
    private IBatterySensor concreteComponent;

    public BatterySensor(IBatterySensor concreteComponent) {
        super(concreteComponent);
        this.concreteComponent = concreteComponent;
    }

    public IBatteryManager getManager() {
        return concreteComponent.getManager();
    }
    



}
