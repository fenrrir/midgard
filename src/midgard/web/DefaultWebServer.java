/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web;

import com.sun.spot.io.j2me.radiogram.RadiogramConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.Datagram;
import midgard.components.IComponentManager;
import midgard.events.IEvent;
import midgard.services.Service;


/**
 *
 * @author fenrrir
 */


public class DefaultWebServer extends Service implements IWebServer {

    private RadiogramConnection conn;
    private IHTTPServer server;
    private Datagram input, output;
    private IComponentManager componentManager;

    private boolean isRunning = false;
    private Thread thread;


    public String[] getRequiredInterfaces() {
        return new String [] {
            IHTTPServer.class.getName(),
            IComponentManager.class.getName()
        };
    }


    public void initialize(){
        super.initialize();
        server = (IHTTPServer) getConnectedComponents()
                    .get(IHTTPServer.class.getName());

        componentManager = (IComponentManager) getConnectedComponents()
                    .get(IComponentManager.class.getName());

        
    }

    public void startService() {
        super.startService();

        server.registerEventListener(this);
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stopService() {
        super.stopService();
        isRunning = false;
        server.removeEventListener(this);
        componentManager.destroyComponent(server);

        thread.interrupt();

        isRunning = false;
        server = null;
    }

    public void newEventArrived(IEvent event) {
        super.newEventArrived(event);
        fireEvent(event);
    }

 

    public void run(){
        try {
            handleConnections();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    public void addWebComponent(IWebComponent component){
        URLView view;
        Vector views;
        IWebApplication app = component.getWebApplication();
        views = app.getViews();
        for(int i=0; i< views.size(); i++){
            view = (URLView) views.elementAt(i);
            server.addView(view);
        }

    }

    private void handleConnections() throws IOException {
        

        try {

            while (isRunning) {

                
                conn = (RadiogramConnection) Connector.open("radiogram://:80");

                input = conn.newDatagram(conn.getMaximumLength());
                output = conn.newDatagram(conn.getMaximumLength());

                System.err.println("Webserver listening");
                conn.receive(input);
                System.err.println("Webserver received request");
                server.handleRequest(input, output);
                System.err.println("Webserver process request");
                conn.send(output);
                System.err.println("Webserver send request");
                conn.close();

                
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            conn.close();
        }
    }

}

