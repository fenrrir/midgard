/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midgard.config;

import midgard.componentmodel.Component;
import midgard.utils.FileUtils;
import midgard.web.json.JSONException;
import midgard.web.json.JSONObject;

/**
 *
 * @author fenrrir
 */
public class DefaultDefaultConfig extends Component implements IDefaultConfig { //DefaultConfigImpl

    private JSONObject json = null;
    private final String REPO = "/defaultConf.json";

    public void initialize() {
        super.initialize();
        open();       
    }

    public void destroy() {
        pause();
        super.destroy();
    }

    public void pause() {
        super.pause();
        json = null;
    }

    public void resume() {
        super.resume();
        open();
    }

    public long getSleepTime() {
        try {
            return json.getLong("sleep");
        } catch (JSONException ex) {
            ex.printStackTrace();
            return 5000;
        }
    }

    private void open() {
        try {
            json = new JSONObject(FileUtils.readFile(REPO));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
}
