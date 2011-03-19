/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.app;

import midgard.pubsubhubbub.ISubscriber;
import midgard.pubsubhubbub.events.SubscriptionEvent;
import midgard.sensors.events.NetworkEvent;
import midgard.web.Request;
import midgard.web.events.AsyncMessageEvent;
import midgard.web.json.JSONException;
import midgard.web.json.JSONObject;

/**
 *
 * @author fenrrir
 */
public class TestClientApp extends App {
    private ISubscriber subscriber;

    public String[] getRequiredInterfaces() {
        return new String [] {
            ISubscriber.class.getName()
        };
    }

    public void destroy() {
        super.destroy();
        //System.err.println(getName() + " destroy");
    }

    public void initialize() {
        super.initialize();
        //System.err.println("@@@@@@ " + getName() + " load");

        subscriber = (ISubscriber) getConnectedComponents()
                .get(ISubscriber.class.getName());
/*
        HttpConnector conn = new HttpConnector();
        try {
            conn.connect("c0a8.0f66.0000.1001");
            String result = conn.get("/foobar");
            System.err.println("@@@@ Reposta eh " + result );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
 *
 *
 */
        //subscriber.register(this, "/sensor/temperature", "c0a8.0080.0000.1001");
        subscriber.register(this, "/sensor/temperature", "c0a8.0080.0000.1001");
subscriber.register(this, "/sensor/temperature", "c0a8.0080.0000.1002");
subscriber.register(this, "/sensor/temperature", "c0a8.0080.0000.1003");
subscriber.register(this, "/sensor/temperature", "c0a8.0080.0000.1004");
subscriber.register(this, "/sensor/temperature", "c0a8.0080.0000.1005");




        
        
    }

    public void load() {
        super.load();
        //System.err.println("@@@@@@ " + getName() + " load");
    }

    public void pause() {
        super.pause();
        //System.err.println(getName() + " pause");
    }

    public void resume() {
        super.resume();
        //System.err.println(getName() + " resume");
    }

    public void handleNetworkEvent(NetworkEvent event) {

        //System.err.println("@@@@@@@@@@@@@@@@@@Event class " + event.getClass().getName());

        if (event instanceof SubscriptionEvent){
            Request request = (Request) event.getContentObject();
            String data =
                    request.parms.getProperty("value");
            try {
                JSONObject json = new JSONObject(data);
                String address = json.getString("address");
                int counter = Integer.parseInt(request.parms.getProperty("counter"));
                System.err.println(address + " " + counter + " " +  System.currentTimeMillis());

                
            } catch (JSONException ex) {
                ex.printStackTrace();
            }

        }

        if (event instanceof AsyncMessageEvent){
            String data  = (String )event.getContentObject();
            //System.err.println("@@@@@@@@@@@@@@@@@@@ Mensagem de resposta da requisicoes HTTP" + data);
        }
    }



}
