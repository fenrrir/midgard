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

import midgard.pubsubhubbub.events.PublicationEvent;
import midgard.events.IEvent;
import midgard.pubsubhubbub.events.PublicationSensorEvent;
import midgard.pubsubhubbub.events.PublicationSensorEventData;
import midgard.sensors.network.INetworkSensor;
import midgard.services.Service;
import midgard.web.Response;
import midgard.web.events.ResponseEvent;

/**
 *
 * @author fenrrir
 */
public class DefaultPublisher extends Service implements IPublisher {
    private INetworkSensor networkSensor;

    public String[] getRequiredInterfaces() {
        return new String [] {
            INetworkSensor.class.getName(),
        };
    }

    public void initialize() {
        super.initialize();
        networkSensor = (INetworkSensor) getConnectedComponents()
                .get(INetworkSensor.class.getName());
        networkSensor.registerEventListener(this);
    }

    public void destroy() {
        super.destroy();
        networkSensor.removeEventListener(this);
        networkSensor = null;
    }

    public void newEventArrived(IEvent event) {        
        if(! (event instanceof ResponseEvent) ){
            return;
        }
        Object content = event.getContentObject();
        Response response = (Response) content;
        fireEvent( new PublicationEvent(content));
    }

    public void publish(String uri, String content) {
        PublicationSensorEventData data
                = new PublicationSensorEventData(uri, content);
        fireEvent( new PublicationSensorEvent(data));
    }




}
