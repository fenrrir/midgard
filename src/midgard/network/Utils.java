/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.network;

import com.sun.spot.peripheral.radio.RadioFactory;
import com.sun.spot.util.IEEEAddress;

/**
 *
 * @author fenrrir
 */
public class Utils {

    public static String getAddress(){
        long longMyAddress = RadioFactory.getRadioPolicyManager().getIEEEAddress();
        String myAddress = new IEEEAddress(longMyAddress).asDottedHex();
        return myAddress;
        
    }

}
