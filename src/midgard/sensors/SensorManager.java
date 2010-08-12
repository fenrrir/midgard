/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors;

import midgard.events.IEvent;

/**
 *
 * @author fenrrir
 */
public class SensorManager extends Sensor{
    private static SensorManager instance = null;

    private AccelerometerSensor acc;
    private LightSensor light;
    private BatterySensor batt;
    private NetworkSensor net;
    private ThemperatureSensor themp;


    private SensorManager() {
        this.acc = new AccelerometerSensor();
        this.light = new LightSensor();
        this.batt = new BatterySensor();
        this.net = new NetworkSensor();
        this.themp = new ThemperatureSensor();
    }

    public SensorManager getInstance(){
        if (instance == null)
            return new SensorManager();
        return instance;
    }

    public AccelerometerSensor getAccelerometerSensor() {
        return acc;
    }

    public BatterySensor getBatterySensor() {
        return batt;
    }

    public LightSensor getLight() {
        return light;
    }

    public NetworkSensor getNetworkSensor() {
        return net;
    }

    public ThemperatureSensor getThemperatureSensor() {
        return themp;
    }

    public void destroy() {
        acc.destroy();
        themp.destroy();
        net.destroy();
        light.destroy();
        batt.destroy();
    }

    public void initialize() {
        acc.initialize();
        themp.initialize();
        net.initialize();
        light.initialize();
        batt.initialize();
    }

    public void load() {
        acc.load();
        themp.load();
        net.load();
        light.load();
        batt.load();
    }

    public void pause() {
        acc.pause();
        themp.pause();
        net.pause();
        light.pause();
        batt.pause();
    }

    public void resume() {
        acc.resume();
        themp.resume();
        net.resume();
        light.resume();
        batt.resume();
    }

    public void newEventArrived(IEvent event) {
    }

}
