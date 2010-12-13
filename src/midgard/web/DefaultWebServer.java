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
import midgard.app.IAppRepositoryManager;
import midgard.components.IComponentManager;
import midgard.events.IEvent;
import midgard.kernel.ClassLoader;
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
    private IAppRepositoryManager appRepositoryManager;
    private Vector webApplications;
    private boolean isRunning = false;
    private Thread thread;

    public String[] getRequiredInterfaces() {
        return new String[]{
                    IHTTPServer.class.getName(),
                    IComponentManager.class.getName(),
                    IAppRepositoryManager.class.getName()
                };
    }

    public void initialize() {
        super.initialize();
        server = (IHTTPServer) getConnectedComponents().get(IHTTPServer.class.getName());

        componentManager = (IComponentManager) getConnectedComponents().get(IComponentManager.class.getName());

        appRepositoryManager = (IAppRepositoryManager) getConnectedComponents().get(IAppRepositoryManager.class.getName());


    }

    public void startService() {
        super.startService();

        String webAppName;
        IWebApplication webApp;

        server.registerEventListener(this);

        System.err.println("WebServer Starting...");
        Vector userWebApplications = appRepositoryManager.listWebApplications();
        for (int i = 0; i < userWebApplications.size(); i++) {
            webAppName = (String) userWebApplications.elementAt(i);
            webApp = (IWebApplication) initializeWebAppByName(webAppName);
            if (webApp != null) {
                addWebApplication(webApp);
            }
        }



        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stopService() {
        super.stopService();
        isRunning = false;
        server.removeEventListener(this);

        String uri;
        IWebApplication webApp;
        Vector URIs;

        for (int i = 0; i < webApplications.size(); i++) {
            webApp = (IWebApplication) webApplications.elementAt(i);
            URIs = webApp.getURIs();
            for (int j = 0; j < URIs.size(); j++) {
                uri = (String) URIs.elementAt(j);
                server.removeViewWithURI(uri);
            }

        }


        componentManager.destroyComponent(server);

        thread.interrupt();

        isRunning = false;
        server = null;
    }

    public Object initializeWebAppByName(String name) {
        try {
            return ClassLoader.newInstanceOf(name);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void newEventArrived(IEvent event) {
        super.newEventArrived(event);
        fireEvent(event);
    }

    public void run() {
        try {
            handleConnections();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addWebApplication(IWebApplication application) {
        String uri;
        Vector appURIs = application.getURIs();


        System.err.println("Webserver add application "
                + application.getClass().getName());

        for (int i = 0; i < appURIs.size(); i++) {
            uri = (String) appURIs.elementAt(i);
            server.addView(new URLView(uri, application));
        }
    }

    public Vector listWebApplications() {
        return webApplications;
    }

    public void removeWebApplication(IWebApplication application) {
        String uri;
        Vector appURIs = application.getURIs();
        for (int i = 0; i < appURIs.size(); i++) {
            uri = (String) appURIs.elementAt(i);
            server.removeViewWithURI(uri);
        }
    }

    private void handleConnections() throws IOException {


        while (isRunning) {
            try {

                conn = (RadiogramConnection) Connector.open("radiogram://:80");
                input = conn.newDatagram(conn.getMaximumLength());
                output = conn.newDatagram(conn.getMaximumLength());

                System.err.println("Webserver listening");
                conn.receive(input);
                output.reset();
                output.setAddress(input);
                System.err.println("Webserver received request");
                server.handleRequest(input, output);
                System.err.println("Webserver process request" + output.readUTF());
                conn.send(output);
                System.err.println("Webserver send request");




            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                conn.close();
            }
        }
    }
}
