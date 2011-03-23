
package midgard.app.tests;

import com.sun.spot.util.Utils;
import java.util.Vector;
import midgard.app.App;
import midgard.app.events.ChangeSleepTimeEvent;
import midgard.kernel.Debug;
import midgard.pubsubhubbub.ISubscriber;
import midgard.pubsubhubbub.events.SubscriptionEvent;
import midgard.sensors.battery.BatterySensorData;
import midgard.sensors.events.BatteryEvent;
import midgard.sensors.events.NetworkEvent;
import midgard.web.IWebApplication;
import midgard.web.Request;
import midgard.web.Response;
import midgard.web.apps.Helper;
import midgard.web.json.JSONException;
import midgard.web.json.JSONObject;


public class MyAggregateWebApp extends App implements IWebApplication{
    private boolean temp;
    private double temperature;
    private double light;
    private ISubscriber subscriber;
    private String [] topics = {"/sensor/temperature", "/sensor/light"};

    public void initialize() {
        super.initialize();
        subscriber = (ISubscriber) getConnectedComponents().get(ISubscriber.class.getName());
        subscriber.register(this, topics, "c0a8.0f66.0000.1001");
    }

    public String[] getRequiredInterfaces() {
        return new String[]{
                    ISubscriber.class.getName()
                };
    }

    public void handleBatteryEvent(BatteryEvent event) {
        BatterySensorData data = (BatterySensorData) event.getContentObject();
    }





    public void handleNetworkEvent(NetworkEvent event) {
        Request request;
        JSONObject json;


        if ( event instanceof SubscriptionEvent){
            try {
                request = (Request) event.getContentObject();
                json = new JSONObject((String) request.parms.get("value"));
                if (request.parms.get("topic").equals("/sensor/temperature")) {
                    temperature = json.getDouble("value");
                    Debug.debug("Remote temperature " + temperature );
                }
                else {
                    light = json.getDouble("value");
                    Debug.debug("Remote light " + light );
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
