/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.pubsubhubbub;

import midgard.componentmodel.IComponent;

/**
 *
 * @author fenrrir
 */
public interface IPublisher extends IComponent {
    public void publish(String uri, String content);

}
