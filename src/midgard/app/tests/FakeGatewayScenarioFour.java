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

import java.io.IOException;
import midgard.web.http.HttpConnector;

/**
 *
 * @author fenrrir
 */
public class FakeGatewayScenarioFour extends FakeGatewayScenarioThree{

    public void callRemoteAdaptation(){
        String response;
        HttpConnector conn = new HttpConnector();
        try {
            conn.connect("c0a8.0f66.0000.1001");
            response = conn.get("/sensor/changeSensorManager");
            conn.closeConnection();
            //TODO: catch error
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
