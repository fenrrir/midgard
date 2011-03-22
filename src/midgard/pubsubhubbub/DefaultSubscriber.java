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
package midgard.pubsubhubbub;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import midgard.events.IEvent;
import midgard.events.IListener;
import midgard.kernel.Debug;
import midgard.utils.NetworkUtils;
import midgard.services.Service;
import midgard.utils.StringUtils;
import midgard.web.Request;
import midgard.web.http.HttpConnector;

/**
 *
 * @author fenrrir
 */
public class DefaultSubscriber extends Service implements ISubscriber {

    private Hashtable subscriptionsByTopic;
    private IHub hub;
    String myAddress;

    public void initialize() {
        super.initialize();
        subscriptionsByTopic = new Hashtable();
        hub = (IHub) getConnectedComponents()
                .get(IHub.class.getName());
        hub.registerEventListener(this);
        myAddress = NetworkUtils.getAddress();
    }

    public void destroy() {
        super.destroy();
        hub.removeEventListener(this);
        hub = null;
        subscriptionsByTopic.clear();
        subscriptionsByTopic = null;
    }



    public String[] getRequiredInterfaces() {
        return new String [] {
            IHub.class.getName(),
        };
    }


    public void register(IListener listener, String topic) {
        Vector subscriptions;

        if (subscriptionsByTopic.containsKey(topic)) {
            subscriptions = (Vector) subscriptionsByTopic.get(topic);
            Subscription sub = new Subscription(listener, topic);
            if (!subscriptions.contains(sub)) {
                subscriptions.addElement(sub);
            }
        } else {
            subscriptions = new Vector();
            Subscription sub = new Subscription(listener, topic);
            subscriptions.addElement(sub);
            subscriptionsByTopic.put(topic, subscriptions);
        }

    }

    public void unRegister(IListener listener, String topic) {
        Vector subscriptions;
        if (subscriptionsByTopic.containsKey(topic)) {
            subscriptions = (Vector) subscriptionsByTopic.get(topic);
            Subscription sub = new Subscription(listener, topic);
            subscriptions.removeElement(sub);
        }
    }

    public void register(IListener listener, String topic, String address) {
        Vector subscriptions;

        String addressUpper = address.toUpperCase();

        if (subscriptionsByTopic.containsKey(topic)) {
            subscriptions = (Vector) subscriptionsByTopic.get(topic);
            Subscription sub = new Subscription(listener, topic, addressUpper);
            if (!subscriptions.contains(sub)) {
                subscriptions.addElement(sub);
            }
        } else {
            subscriptions = new Vector();
            Subscription sub = new Subscription(listener, topic, addressUpper);
            subscriptions.addElement(sub);
            subscriptionsByTopic.put(topic, subscriptions);
        }
        registerOnRemoteHub(topic, address);
    }

    public void unRegister(IListener listener, String topic, String address) {
        String addressUpper = address.toUpperCase();
        Vector subscriptions;
        if (subscriptionsByTopic.containsKey(topic)) {
            subscriptions = (Vector) subscriptionsByTopic.get(topic);
            Subscription sub = new Subscription(listener, topic, addressUpper);
            subscriptions.removeElement(sub);
        }
    }

    public void newEventArrived(IEvent event) {
        Request request =  (Request) event.getContentObject();
        String topic = request.parms.getProperty("topic");
        Vector subscriptions = (Vector) subscriptionsByTopic.get(topic);
        Subscription sub;

        if (subscriptions != null){
            for (int i=0; i < subscriptions.size(); i++){
                sub = (Subscription) subscriptions.elementAt(i);
                sub.notifyListener(request);
            }
        }
    }

    private void registerOnRemoteHub(String topic, String address) {
        try {

            Hashtable data = new Hashtable();
            data.put("topic", topic);
            data.put("address", myAddress);
            HttpConnector connector = new HttpConnector();
            connector.connect(address);
            connector.post("/subscribe", data);
            connector.closeConnection();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void register(IListener listener, String[] topics, String address) {
        Vector subscriptions;

        String addressUpper = address.toUpperCase();

        for( int i=0; i < topics.length; i++){
            String topic = topics[i];

            if (subscriptionsByTopic.containsKey(topic)) {
                subscriptions = (Vector) subscriptionsByTopic.get(topic);
                Subscription sub = new Subscription(listener, topic, addressUpper);
                if (!subscriptions.contains(sub)) {
                    subscriptions.addElement(sub);
                }
            } else {
                subscriptions = new Vector();
                Subscription sub = new Subscription(listener, topic, addressUpper);
                subscriptions.addElement(sub);
                subscriptionsByTopic.put(topic, subscriptions);
            }

        }
        registerOnRemoteHub(StringUtils.join(topics), address);
    }


}
