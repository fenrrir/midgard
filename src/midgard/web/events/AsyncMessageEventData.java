/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web.events;

/**
 *
 * @author fenrrir
 */
public class AsyncMessageEventData {
    private String message, address;

    public AsyncMessageEventData(String message, String address) {
        this.message = message;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
