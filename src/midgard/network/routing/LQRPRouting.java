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

package midgard.network.routing;

import com.sun.spot.peripheral.radio.ILowPan;
import com.sun.spot.peripheral.radio.LowPan;
import com.sun.spot.peripheral.radio.RadioFactory;
import com.sun.spot.peripheral.radio.mhrp.lqrp.LQRPManager;
import com.sun.spot.peripheral.radio.routing.RouteInfo;
import com.sun.spot.peripheral.radio.routing.RouteTable;
import java.util.Enumeration;
import midgard.services.Service;

/**
 *
 * @author fenrrir
 */
public class LQRPRouting extends Service implements IRouting {
   private LQRPManager manager;

    public int countNeighbors() {
        int neighbors = 0;
        RouteTable routeTable = manager.getRoutingTable();
        Enumeration e = routeTable.getAllEntries();
        while (e.hasMoreElements()) {
            RouteInfo ri = ((RouteInfo)e.nextElement());
            if (ri.destination == ri.nextHop)
                neighbors++;
        }
        return neighbors;
    }

    public RoutingTable getRoutingTable() {
        RoutingTable routingTable = new RoutingTable();
        Enumeration e = manager.getRoutingTable().getAllEntries();
        while (e.hasMoreElements()) {
            routingTable.addEntry((RoutingInfo)e.nextElement());
        }
        return routingTable;
    }

    public void destroy() {
        super.destroy();
        manager.stop();
    }

    public void initialize() {
        super.initialize();
        ILowPan lowPan;
        lowPan = LowPan.getInstance();
        lowPan.setRoutingManager(manager);
        manager.initialize(RadioFactory.getRadioPolicyManager().getIEEEAddress(), lowPan);
        manager.start();
    }

    public void load() {
        super.load();
    }

    public void pause() {
        super.pause();
        manager.pause();
    }

    public void resume() {
        super.resume();
        manager.resume();
    }


}