/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.app;

import midgard.pubsubhubbub.ISubscriber;
import midgard.pubsubhubbub.events.SubscriptionEvent;
import midgard.sensors.events.NetworkEvent;
import midgard.web.Request;

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
        System.err.println(getName() + " destroy");
    }

    public void initialize() {
        super.initialize();
        System.err.println("@@@@@@ " + getName() + " load");

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
        subscriber.register(this, "/sensor/temperature", "c0a8.0f66.0000.1001");
        
    }

    public void load() {
        super.load();
        System.err.println("@@@@@@ " + getName() + " load");
    }

    public void pause() {
        super.pause();
        System.err.println(getName() + " pause");
    }

    public void resume() {
        super.resume();
        System.err.println(getName() + " resume");
    }

    public void handleNetworkEvent(NetworkEvent event) {

        if (event instanceof SubscriptionEvent){
            Request request = (Request) event.getContentObject();
            System.err.println("$$$ Received " +
                    request.parms.getProperty("value"));
        }
    }



}
