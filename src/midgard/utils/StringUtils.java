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

package midgard.utils;

import java.util.Vector;

/**
 *
 * @author fenrrir
 */
public class StringUtils {
    public static String join(String [] parts){
        String result = parts[0];
        for (int i=1; i<parts.length; i++){
            result += (";" + parts[i]);
        }
        return result;
    }

    public static Vector split(String str){
        Vector vector = new Vector();
        int index = 0;
        int firstOccurrence;

        while(index < str.length()){
            firstOccurrence = str.indexOf(";", index);

            if (firstOccurrence != -1){
                vector.addElement(str.substring(index, firstOccurrence));
            }else{
                vector.addElement(str.substring(index));
                break;
            }
            index = firstOccurrence + 1;
        }
        return vector;

    }

}
