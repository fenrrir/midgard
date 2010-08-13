/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.componentmodel;

import java.util.Hashtable;

/**
 *
 * @author fenrrir
 */
public interface IConfiguration {
    void setConfigurationParameters(Hashtable params);
    void setConfigurationParameter(String name, Object value);
    Hashtable getConfigurationParameters();
    Object getConfigurationParameter(String name);

}
