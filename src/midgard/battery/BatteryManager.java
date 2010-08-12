/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.battery;

import midgard.componentmodel.IComponent;
import midgard.componentmodel.ProxyComponent;

/**
 *
 * @author fenrrir
 */
public class BatteryManager extends ProxyComponent implements IBatteryManager{

    private IBatteryManager concreteComponent;

    public BatteryManager(IBatteryManager concreteComponent) {
        super((IComponent) concreteComponent );
        this.concreteComponent = concreteComponent;
    }



    public long getTime() {
        return concreteComponent.getTime();
    }

    public int getBatteryLevel() {
        return concreteComponent.getBatteryLevel();
    }

    public double getAvailableCapacity() {
        return concreteComponent.getAvailableCapacity();
    }


}
