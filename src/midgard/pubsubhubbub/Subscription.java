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

import midgard.pubsubhubbub.events.SubscriptionEvent;
import midgard.events.IListener;
import midgard.web.Properties;
import midgard.web.Request;

/**
 *
 * @author fenrrir
 */
public class Subscription {

    private String topic, address;
    private IListener listener;

    public Subscription(IListener listener, String topic) {
        this.topic = topic;
        this.listener = listener;
        address = "";
    }

    public Subscription(IListener listener, String topic, String address) {
        this.topic = topic;
        this.address = address;
        this.listener = listener;
    }

    public void notifyListener(Request request) {

        Properties params = request.parms;
        String uri = (String) params.getProperty("topic");
        String addressParam = (String) params.getProperty("address");

        if (uri.equals(topic)) {

            if (address.equals("")) {
                listener.newEventArrived(new SubscriptionEvent(request));
            } else if (address.equals(addressParam)) {               
                listener.newEventArrived(new SubscriptionEvent(request));
            }
        }

    }

    public IListener getListener() {
        return listener;
    }

    public void setListener(IListener listener) {
        this.listener = listener;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Subscription other = (Subscription) obj;
        if ((this.topic == null) ? (other.topic != null) : !this.topic.equals(other.topic)) {
            return false;
        }
        if ((this.address == null) ? (other.address != null) : !this.address.equals(other.address)) {
            return false;
        }
        if (this.listener != other.listener && (this.listener == null || !this.listener.equals(other.listener))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (this.topic != null ? this.topic.hashCode() : 0);
        hash = 43 * hash + (this.address != null ? this.address.hashCode() : 0);
        hash = 43 * hash + (this.listener != null ? this.listener.hashCode() : 0);
        return hash;
    }
}
