/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.utils;

import com.sun.spot.util.Utils;
import java.util.Vector;
import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

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
