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
package midgard.web.tcp;

import com.sun.spot.ipv6.tcp.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import midgard.kernel.Debug;
import midgard.web.IHTTPServer;

/**
 *
 * @author fenrrir
 */
public class TCPWorker implements Runnable {

    private IHTTPServer httpServer;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    private Socket socket;
    private Thread thread;

    public TCPWorker(IHTTPServer httpServer, Socket socket) {
        this.httpServer = httpServer;
        this.outputStream = new DataOutputStream(socket.getOutputStream());
        this.inputStream = new DataInputStream(socket.getInputStream());
        this.socket = socket;
        thread = new Thread(this);
        thread.start();

    }

    public void run() {
        try {
            String request = inputStream.readUTF();
            String response = httpServer.handleRequest(request);
            outputStream.writeUTF(response);
            outputStream.close();
            Debug.debug("[APP] Done, closing up", 1);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
