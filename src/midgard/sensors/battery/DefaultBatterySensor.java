/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors.battery;

import com.sun.spot.peripheral.IBattery;
import com.sun.spot.peripheral.Spot;
import midgard.battery.IBatteryManager;
import midgard.sensors.Sensor;
import midgard.sensors.events.BatteryEvent;

/**
 *
 * @author fenrrir
 */
public class DefaultBatterySensor extends Sensor implements IBatterySensor{
    private IBatteryManager manager;
    private IBattery battery;

    public String[] getRequiredInterfaces() {
        return new String [] {
            IBatteryManager.class.getName()
        };
    }

    public void initialize(){
        manager =  (IBatteryManager)
                getConnectedComponents().get(IBatteryManager.class.getName());

        super.initialize();
    }



    public IBatteryManager getManager(){
        return manager;
    }

    public void collect() {
        IBatteryData data = new DefaultBatteryData(battery);
        fireEvent( new BatteryEvent( data ) );
    }

    public void disableSensor() {
        battery = null;
    }

    public void initSensor() {
        battery = Spot.getInstance().getPowerController().getBattery();
    }



}
