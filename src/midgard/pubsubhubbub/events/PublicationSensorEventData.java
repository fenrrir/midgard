/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.pubsubhubbub.events;

/**
 *
 * @author fenrrir
 */
public class PublicationSensorEventData {
    private String uri, data;

    public PublicationSensorEventData(String uri, String data) {
        this.uri = uri;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
