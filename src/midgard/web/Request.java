/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web;

/**
 *
 * @author fenrrir
 */
public class Request {
      public Request(String method, String uri) {
        this.method = method;
        this.uri = uri;
        this.uriPrefix = "";
    }

    public String method;

    public String uri;

    public String uriPrefix;

    public Properties header = new Properties();

    public Properties parms = new Properties();

}
