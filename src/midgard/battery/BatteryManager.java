/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.battery;

import midgard.componentmodel.IComponent;
import midgard.services.IService;
import midgard.services.ProxyService;

/**
 *
 * @author fenrrir
 */
public class BatteryManager extends ProxyService implements IBatteryManager{

    private IBatteryManager concreteComponent;

    public void setConcreteComponent(IComponent component) {
        super.setConcreteComponent(component);
        concreteComponent = (IBatteryManager) component;
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
