/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web.tests;

import midgard.web.IWebApplication;
import midgard.web.IWebComponent;

/**
 *
 * @author fenrrir
 */
public class FakeComponent implements IWebComponent {

    public IWebApplication getWebApplication() {
        return new FakeWebApp();
    }

}
