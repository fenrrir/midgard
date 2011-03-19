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