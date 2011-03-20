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
package midgard.web.apps.sensors;

import java.util.Vector;
import midgard.componentmodel.Component;
import midgard.events.IEvent;
import midgard.pubsubhubbub.IPublisher;
import midgard.sensors.light.ILightSensor;
import midgard.web.IWebApplication;
import midgard.web.Request;
import midgard.web.Response;
import midgard.web.apps.Helper;
import midgard.web.json.JSONException;
import midgard.web.json.JSONObject;

/**
 *
 * @author fenrrir
 */
public class LightWebApp extends Component implements IWebApplication {

    private Vector uris;
    private ILightSensor lightSensor;
    private IPublisher publisher;
    private int lightLevel;
    private JSONObject json;
    private final String URI = "/sensor/light";

    public void initialize() {
        super.initialize();
        uris = new Vector();
        uris.addElement(URI);

        lightSensor = (ILightSensor) getConnectedComponents()
                .get(ILightSensor.class.getName());

        publisher = (IPublisher) getConnectedComponents()
                .get(IPublisher.class.getName());

        lightSensor.registerEventListener(this);
        json = new JSONObject();

        try {
            json.put("value", false);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

    }

    public void destroy() {
        super.destroy();
        lightSensor.removeEventListener(this);
        lightSensor = null;
        publisher = null;
        uris.removeAllElements();
        uris = null;
    }

    public String[] getRequiredInterfaces() {
        return new String[]{
                    ILightSensor.class.getName(),
                    IPublisher.class.getName(),};
    }

    public Vector getURIs() {
        return uris;

    }

    public Response serve(Request request) throws Exception {
        if (request.uri.equals(URI)) {
            return Helper.getResponse(json);
        }
        return null;
    }

    public void newEventArrived(IEvent event) {
        super.newEventArrived(event);
        Integer integer = (Integer) event.getContentObject();
        lightLevel = integer.intValue();

        try {
            json.put("value", lightLevel);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        publisher.publish(URI, json.toString());


    }
}
