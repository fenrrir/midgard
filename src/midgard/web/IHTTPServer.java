/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Datagram;
import midgard.componentmodel.IComponent;

/**
 *
 * @author fenrrir
 */
public interface IHTTPServer extends IComponent {
    public void handleRequest(Datagram input, Datagram output) throws IOException;
    public void addView(URLView view);
}
