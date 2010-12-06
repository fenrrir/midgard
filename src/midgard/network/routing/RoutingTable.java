/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.network.routing;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author fenrrir
 */
public class RoutingTable {

    private Hashtable infos;

    public void addEntry (RoutingInfo routingInfo) {
        infos.put(new Long(routingInfo.getDestination()), routingInfo);
    }

    public RoutingInfo getEntry(long address){
        RoutingInfo routingInfo = null;
        routingInfo = (RoutingInfo)infos.get(new Long(address));
        return routingInfo;
    }

    public Enumeration getAllEntries(){
        return infos.elements();
    }

    public int getSize() {
        return infos.size();
    }


}