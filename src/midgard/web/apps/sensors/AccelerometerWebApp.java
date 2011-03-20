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
import midgard.sensors.accelerometer.IAccelerometerData;
import midgard.sensors.accelerometer.IAccelerometerSensor;
import midgard.sensors.temperature.TemperatureSensorData;
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
public class AccelerometerWebApp extends Component implements IWebApplication {

    private Vector uris;
    private IAccelerometerSensor accelerometerSensor;
    private IPublisher publisher;
    private double relativeAccel;
    private JSONObject json;
    private final String URI = "/sensor/accelerometer";

    public void initialize() {
        super.initialize();
        uris = new Vector();
        uris.addElement(URI);

        accelerometerSensor = (IAccelerometerSensor) getConnectedComponents()
                .get(IAccelerometerSensor.class.getName());

        publisher = (IPublisher) getConnectedComponents()
                .get(IPublisher.class.getName());

        accelerometerSensor.registerEventListener(this);
        json = new JSONObject();

        try {
            json.put("value", false);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

    }

    public void destroy() {
        super.destroy();
        accelerometerSensor.removeEventListener(this);
        accelerometerSensor = null;
        publisher = null;
        uris.removeAllElements();
        uris = null;
    }

    public String[] getRequiredInterfaces() {
        return new String[]{
                    IAccelerometerSensor.class.getName(),
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
        IAccelerometerData data = (IAccelerometerData) event.getContentObject();
        relativeAccel = data.getRelativeAccel();

        try {
            json.put("value", relativeAccel);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        publisher.publish(URI, json.toString());


    }
}
