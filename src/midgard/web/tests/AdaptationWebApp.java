/*
* Copyright (C) 2011 Rodrigo Pinheiro Marques de Araujo
*
* This program is free software; you can redistribute it and/or modify it under
* the terms of the GNU General Public License as published by the Free Software
* Foundation; either version 2 of the License, or (at your option) any later
* version.
*
* This program is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
* FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
* details.
*
* You should have received a copy of the GNU General Public License along with
* this program; if not, write to the Free Software Foundation, Inc., 51
* Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
*/

package midgard.web.tests;

import java.util.Vector;
import midgard.app.events.DeepSleepModeEvent;
import midgard.app.events.LightSleepModeEvent;
import midgard.componentmodel.Component;
import midgard.web.IWebApplication;
import midgard.web.Request;
import midgard.web.Response;
import midgard.utils.ResponseUtils;
import midgard.utils.ResponseUtils;
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
        return ResponseUtils.getResponse(response);
    }

}
