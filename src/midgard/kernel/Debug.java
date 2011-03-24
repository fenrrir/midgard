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

import midgard.utils.NetworkUtils;

/**
 *
 * @author fenrrir
 */
public class Debug {
    private static String address = "";
    public static boolean on = true;
    public static int level = 0;

    private static void print(String msg, int i_level){

        if (on && (i_level >= level)){
            if(address.equals("")){
                address = NetworkUtils.getAddress();
            }

            System.err.println(address + " " + level + " " + msg);
        }
    }

    public static void debug(String msg){
        print(msg, 0);
    }

    public static void debug(String msg, int i_level){
            print(msg, i_level);
    }

}
