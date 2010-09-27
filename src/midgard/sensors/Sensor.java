/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.sensors;

import midgard.componentmodel.Component;

/**
 *
 * @author fenrrir
 */
public abstract class Sensor extends Component implements ISensor{
    
    public void destroy() {
        super.destroy();
        disableSensor();
    }

    public void initialize() {
        super.initialize();
        initSensor();
    }

    public void pause() {
        super.pause();
        disableSensor();
    }

    public void resume() {
        super.resume();
        initSensor();
    }

}
