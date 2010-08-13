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

    private DefaultAccelerometerSensor acc;
    private DefaultLightSensor light;
    private DefaultBatterySensor batt;
    private DefaultNetworkSensor net;
    private DefaultThemperatureSensor themp;


    private SensorManager() {
        this.acc = new DefaultAccelerometerSensor();
        this.light = new DefaultLightSensor();
        this.batt = new DefaultBatterySensor();
        this.net = new DefaultNetworkSensor();
        this.themp = new DefaultThemperatureSensor();
    }

    public SensorManager getInstance(){
        if (instance == null)
            return new SensorManager();
        return instance;
    }

    public DefaultAccelerometerSensor getAccelerometerSensor() {
        return acc;
    }

    public DefaultBatterySensor getBatterySensor() {
        return batt;
    }

    public DefaultLightSensor getLight() {
        return light;
    }

    public DefaultNetworkSensor getNetworkSensor() {
        return net;
    }

    public DefaultThemperatureSensor getThemperatureSensor() {
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

   
}
