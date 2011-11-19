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

package midgard.kernel;

import java.util.Hashtable;
import midgard.utils.NetworkUtils;
import midgard.web.Request;
import midgard.web.Response;

/**
 *
 * @author fenrrir
 */
public class Debug {
    private static String address = "";
    private static boolean enabled = false;
    private static boolean userEnabled = false;
    private static boolean memoryDebug = false;
    private static boolean userMemoryDebug = false;
    private static int level = 0;
    private static boolean userLevel = false;

    private static boolean userPacketLog =  false;
    private static boolean packetLog =  false;

    private static void print(String msg, int i_level){

        if (isEnabled() && (i_level >= getLevel())){
            if(address.equals("")){
                address = NetworkUtils.getAddress();
            }

            System.err.println(address + " " + getLevel() + " " + msg);
        }
    }

    public static void debug(String msg){
        print(msg, 0);
    }

    public static void debug(String msg, int i_level){
            print(msg, i_level);
    }

    public static void showMemoryStats(String context){

        if (memoryDebugEnabled()){

            Runtime runtime = Runtime.getRuntime();
            long free, total;

            runtime.gc();
            free = runtime.freeMemory();
            total = runtime.totalMemory();
            

            debug("###");
            debug("### Context=" + context);
            debug("### Free memory=" + free);
            debug("### Total memory=" + total);
            debug("### Used memory=" + (total - free));
            debug("###");
        }
    }

    /**
     * @return the on
     */
    public static boolean isEnabled() {
        if(!userEnabled){
            if (Midgard.getProperty("debugEnable").equals("true")){
                return true;
            }
            else
                return false;
        }
        return enabled;
    }


    public static void setEnable(boolean enable) {
        enabled = enable;
    }

    public static boolean memoryDebugEnabled() {
        if(!userMemoryDebug){
            if (Midgard.getProperty("memoryDebug").equals("true")){
                return true;
            }
            else
                return false;
        }
        return memoryDebug;
    }

    
    public static void setMemoryDebug(boolean aMemoryDebug) {
        userMemoryDebug = true;
        memoryDebug = aMemoryDebug;
    }

    
    public static int getLevel() {
        if(!userLevel){
            if (Midgard.getProperty("debugLevel") != null){
                return Integer.parseInt(Midgard.getProperty("debugLevel"));
            }
            else
                return -1;
        }
        return level;
    }

    
    public static void setLevel(int aLevel) {
        userLevel = true;
        level = aLevel;
    }

    
    public static boolean packetLogEnabled(){
        if(!userPacketLog){
            if (Midgard.getProperty("packetLog") != null){
                return Midgard.getProperty("packetLog").equals("true");
            }
            else
                return false;
        }
        return packetLog;
    }

    public static void enablePacketLog(){
        userPacketLog = true;
        packetLog = true;
    }

    public static long getPacketStartTime(){
        if (packetLogEnabled()){
            return System.currentTimeMillis();
        }
        else
            return 0;
    }

    public static void showPacketTimeStats(long startTime){
        if(packetLogEnabled()){
            long time = System.currentTimeMillis() - startTime;
            debug("PacketTime: " + time);
        }
    }



}
