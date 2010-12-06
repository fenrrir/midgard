/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.network.routing;

import com.sun.spot.peripheral.radio.routing.RouteInfo;

/**
 *
 * @author fenrrir
 */
public class RoutingInfo {
   private RouteInfo routeInfo;

   public RoutingInfo (long destination, long nextHop,  int hopCount) {
       routeInfo = new RouteInfo(destination, nextHop, hopCount);
   }

   public long getDestination() {
        return routeInfo.getDestination();
   }

   public int getHopCount (){
       return routeInfo.hopCount;
   }

   public long getNextHop() {
        return routeInfo.nextHop;
   }
}