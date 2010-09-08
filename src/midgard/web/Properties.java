/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author fenrrir
 */
public class Properties extends Hashtable {

    public String getProperty(String key) {
        return (String)get(key);
    }

    public Enumeration propertyNames() {
        return keys();
    }
    

}
