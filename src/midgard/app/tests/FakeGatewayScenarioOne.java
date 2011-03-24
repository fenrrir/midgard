/*
 * Copyright (C) 2011 Rodrigo Pinheiro Marques de Araujo
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package midgard.app.tests;

import midgard.pubsubhubbub.ISubscriber;

/**
 *
 * @author fenrrir
 */
public class FakeGatewayScenarioOne extends TestApp {

    private ISubscriber subscriber;
    private final int numClients = 1;
    private final int clientId = 0x1001;
    private final String clientsAddress = "c0a8.0080.0000.";

    public String[] getRequiredInterfaces() {
        return new String[]{
                    ISubscriber.class.getName()
                };
    }

    public void initialize() {
        super.initialize();

        subscriber = (ISubscriber) getConnectedComponents().get(ISubscriber.class.getName());


    }

    public void subscribesInTopics() {
        subscribes("/sensor/temperature");
    }

    public void subscribes(String topic) {
        int id = clientId;
        for (int i=0; i < numClients; i++){
            String address = Integer.toHexString(id);
            subscriber.register(this, topic, clientsAddress + address);
            id +=1;
        }

    }
}
