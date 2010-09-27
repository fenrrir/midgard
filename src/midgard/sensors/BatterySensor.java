/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors;

import midgard.battery.IBatteryManager;
import midgard.componentmodel.IComponent;

/**
 *
 * @author fenrrir
 */
public class BatterySensor extends ProxySensor implements IBatterySensor{
    private IBatterySensor concreteComponent;

    public void setConcreteComponent(IComponent concreteComponent) {
        super.setConcreteComponent(concreteComponent);
        this.concreteComponent = (IBatterySensor) concreteComponent;
    }

    public IBatteryManager getManager() {
        return concreteComponent.getManager();
    }
    



}
