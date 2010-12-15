/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midgard.web.apps.sensors;

import java.util.Vector;
import midgard.componentmodel.Component;
import midgard.events.IEvent;
import midgard.pubsubhubbub.IPublisher;
import midgard.sensors.temperature.ITemperatureSensor;
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
public class TemperatureWebApp extends Component implements IWebApplication {

    private Vector uris;
    private ITemperatureSensor temperature;
    private IPublisher publisher;
    private double temperatureCelsius;
    private JSONObject json;
    private final String URI = "/sensor/temperature";

    public void initialize() {
        super.initialize();
        uris = new Vector();
        uris.addElement(URI);

        temperature = (ITemperatureSensor) getConnectedComponents()
                .get(ITemperatureSensor.class.getName());

        publisher = (IPublisher) getConnectedComponents()
                .get(IPublisher.class.getName());

        temperature.registerEventListener(this);
        json = new JSONObject();

        try {
            json.put("value", false);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

    }

    public void destroy() {
        super.destroy();
        temperature.removeEventListener(this);
        temperature = null;
        publisher = null;
        uris.removeAllElements();
        uris = null;
    }

    public String[] getRequiredInterfaces() {
        return new String[]{
                    ITemperatureSensor.class.getName(),
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
        TemperatureSensorData data = (TemperatureSensorData) event.getContentObject();
        temperatureCelsius = data.getCelsius();

        try {
            json.put("value", temperatureCelsius);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        publisher.publish(URI, json.toString());


    }
}