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
package midgard.web.http;

import com.sun.spot.io.j2me.radiogram.RadiogramConnection;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.microedition.io.Connector;
import javax.microedition.io.Datagram;

/**
 *
 * @author fenrrir
 */
public class HttpConnector {

    private final int TIMEOUT = 10000;
    private boolean connected = false;
    private RadiogramConnection conn = null;
    Datagram datagram;

    public HttpConnector() {

    }

    public void connect(String address) throws IOException {

        conn = (RadiogramConnection) Connector.open("radiogram://" + address + ":80");
        conn.setTimeout(TIMEOUT);

        datagram = conn.newDatagram(conn.getMaximumLength());
        connected = true;
    }

    public void closeConnection() {
        if (connected == false) {
            return;
        }
        connected = false;
        try {
            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void get(String uri) throws IOException {
        String request;
        request = "GET " + uri + " HTTP/1.0\r\n\r\n";
        datagram.writeUTF(request);
        conn.send(datagram);

    }


    public void post(String uri, Hashtable params) throws IOException {
        StringBuffer request = new StringBuffer();
        StringBuffer content = new StringBuffer();
        request.append("POST " + uri + " HTTP/1.0\r\n");
        content.append("\r\n");

        Enumeration keys = params.keys();
        while (keys.hasMoreElements()){
            String skey = (String) keys.nextElement();
            String value = (String) params.get(skey);
            content.append(skey + "=" + value + "&");
        }

        request.append("Content-Length: " + content.toString().length());
        request.append("\r\n");
        request.append("Content-Type: application/x-www-form-urlencoded\r\n");
        request.append(content.toString());
        datagram.writeUTF(request.toString());
        conn.send(datagram);
    }

    


}
