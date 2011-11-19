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

package midgard.app.tests;

import midgard.app.App;
import midgard.kernel.Debug;
import midgard.pubsubhubbub.ISubscriber;
import midgard.sensors.events.NetworkEvent;

/**
 *
 * @author fenrrir
 */
public class FakeGateway extends App {
    private ISubscriber subscriber;

    public String[] getRequiredInterfaces() {
        return new String [] {
            ISubscriber.class.getName()
        };
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

    public void handleNetworkEvent(NetworkEvent event) {
        Debug.debug("@@@@network event" + event);

        /*
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
        }
         */

        
    }



}
