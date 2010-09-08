/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import midgard.services.Service;


/**
 *
 * @author fenrrir
 */


public class DefaultWebServer extends Service implements IWebServer {

    private StreamConnection conn;
    private NanoHttp server;
    private InputStream is;
    private OutputStream os;



    public DefaultWebServer() throws IOException {
        server = new NanoHttp();
        
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

            while (true) {

                
                conn = (StreamConnection) Connector.open("");

                is = conn.openInputStream();
                os = conn.openOutputStream();

                server.handleRequest(is, os);

                os.write(0);
                os.flush();

                is.close();
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            conn.close();
        }
    }

}

