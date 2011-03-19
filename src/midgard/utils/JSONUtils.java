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

import com.sun.spot.util.Utils;
import java.util.Vector;
import midgard.web.json.JSONArray;
import midgard.web.json.JSONException;
import midgard.web.json.JSONObject;

/**
 *
 * @author fenrrir
 */
public class JSONUtils {

    public static Vector JSONArrayToVectorString(JSONArray array){
        Vector v = new Vector();
        for (int i=0; i< array.length(); i++){
            try {
                v.addElement(array.getString(i));
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        return v;
    }

    public static Vector VectorFromJSONObjectKeys(JSONObject json){
        return Utils.enumToVector(json.keys());
    }
}
