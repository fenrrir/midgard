/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
