/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.pubsubhubbub;

import midgard.pubsubhubbub.events.PublicationEvent;
import midgard.componentmodel.Component;
import midgard.events.IEvent;
import midgard.pubsubhubbub.events.PublicationSensorEvent;
import midgard.pubsubhubbub.events.PublicationSensorEventData;
import midgard.sensors.network.INetworkSensor;
import midgard.web.Response;
import midgard.web.events.ResponseEvent;

/**
 *
 * @author fenrrir
 */
public class DefaultPublisher extends Component implements IPublisher {
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
