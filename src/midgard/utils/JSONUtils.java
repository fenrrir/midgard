/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
