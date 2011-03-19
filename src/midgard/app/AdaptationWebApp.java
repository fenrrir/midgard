/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.app;

import java.util.Vector;
import midgard.app.events.DeepSleepModeEvent;
import midgard.app.events.LightSleepModeEvent;
import midgard.componentmodel.Component;
import midgard.web.IWebApplication;
import midgard.web.Request;
import midgard.web.Response;
import midgard.web.apps.Helper;
import midgard.web.json.JSONObject;

/**
 *
 * @author fenrrir
 */
public class AdaptationWebApp extends Component implements IWebApplication{

    public Vector getURIs() {
        Vector uris = new Vector();
        uris.addElement("/sensor/changeSensorManager");
        return uris;
    }

    public Response serve(Request request) throws Exception {
        JSONObject response = new JSONObject();
        String mode =request.parms.getProperty("mode");
        if (mode.equals("deep"))
           fireEvent(new DeepSleepModeEvent(mode));
        else
            fireEvent(new LightSleepModeEvent(mode));
        response.put("value", "ok");
        return Helper.getResponse(response);
    }

}
