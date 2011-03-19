
package midgard.app;

import java.util.Vector;
import midgard.app.events.ChangeSleepTimeEvent;
import midgard.pubsubhubbub.ISubscriber;
import midgard.pubsubhubbub.events.PubSubHubBubNotificationEvent;
import midgard.sensors.events.NetworkEvent;
import midgard.web.IWebApplication;
import midgard.web.Request;
import midgard.web.Response;
import midgard.web.apps.Helper;
import midgard.web.json.JSONException;
import midgard.web.json.JSONObject;


public class MyBatteryAggregateWebApp extends App implements IWebApplication{
    private boolean temp;
    private double temperature;
    private double light;
    private ISubscriber subscriber;

    public void initialize() {
        super.initialize();
        subscriber.register(this, "/sensor/temperature", "c0a8.0080.0000.1001");
        subscriber.register(this, "/sensor/light", "c0a8.0080.0000.1001");

    }



    public void handleNetworkEvent(NetworkEvent event) {
        Request request;
        JSONObject json;
        if ( event instanceof PubSubHubBubNotificationEvent){
            try {
                request = (Request) event.getContentObject();
                json = new JSONObject((String) request.parms.get("value"));
                if (json.getString("uri").equals("/sensor/temperature")) {
                    temperature = json.getDouble("value");
                }
                else {
                    light = json.getDouble("value");
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
    }


    public Vector getURIs() {
        Vector uris = new Vector();
        uris.addElement("/sensor/temperature");
        uris.addElement("/sensor/light");
        return uris;

    }

  public Response serve(Request request) throws Exception {
    JSONObject response = new JSONObject();
    String param;
    if (request.uri.equals("/sensor/temperature")){
        response.put("value", temperature);
        return Helper.getResponse(response);
    }
    else if(request.uri.equals("/sensor/light")){
        response.put("value", light);
        return Helper.getResponse(response);
    }
    else{
        param = request.parms.getProperty("md5");
        fireEvent(new ChangeSleepTimeEvent(""));
        response.put("value", "ok");
        return Helper.getResponse(response);
    }
}
}
