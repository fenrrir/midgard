/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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