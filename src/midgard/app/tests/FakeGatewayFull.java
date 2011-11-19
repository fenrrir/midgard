/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.app.tests;

/**
 *
 * @author fenrrir
 */
public class FakeGatewayFull extends FakeGatewayScenarioTwo {

    public void subscribesInTopics() {
        super.subscribesInTopics();
        subscribe("/battery/state");
    }

}
