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
package midgard.web;

import java.util.Vector;
import midgard.app.IAppRepositoryManager;
import midgard.components.IComponentManager;
import midgard.events.IEvent;
import midgard.network.mailbox.IMessage;
import midgard.sensors.events.NetworkEvent;
import midgard.sensors.network.INetworkSensor;
import midgard.services.Service;

/**
 *
 * @author fenrrir
 */
public class DefaultWebServer extends Service implements IWebServer {

    private IHTTPServer server;    
    private IComponentManager componentManager;
    private IAppRepositoryManager appRepositoryManager;
    private INetworkSensor networkSensor;
    
    private Vector webApplications;
    

    public String[] getRequiredInterfaces() {
        return new String[]{
                    IHTTPServer.class.getName(),
                    IComponentManager.class.getName(),
                    IAppRepositoryManager.class.getName(),
                    INetworkSensor.class.getName()
                };
    }

    public void initialize() {
        super.initialize();
        server = (IHTTPServer) getConnectedComponents().get(IHTTPServer.class.getName());

        componentManager = (IComponentManager) getConnectedComponents().get(IComponentManager.class.getName());

        appRepositoryManager = (IAppRepositoryManager) getConnectedComponents().get(IAppRepositoryManager.class.getName());
        networkSensor = (INetworkSensor) getConnectedComponents().get(INetworkSensor.class.getName());
        networkSensor.registerEventListener(this);
    }

    public void startService() {
        super.startService();

        String webAppName;
        IWebApplication webApp;

        Vector userWebApplications = appRepositoryManager.listWebApplications();
        for (int i = 0; i < userWebApplications.size(); i++) {
            webAppName = (String) userWebApplications.elementAt(i);
            webApp = (IWebApplication) componentManager.resolveComponent(webAppName);
            if (webApp != null) {
                addWebApplication(webApp);
            }
        }

    }

    public void stopService() {
        super.stopService();
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
        server = null;
    }

    public void newEventArrived(IEvent event) {
        if (event instanceof NetworkEvent){
            IMessage message = (IMessage) event.getContentObject();
            String reply = server.handleRequest(message.getContent());
            message.reply(reply);
        }
    }

    

    public void addWebApplication(IWebApplication application) {
        String uri;
        Vector appURIs = application.getURIs();

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

    
}
