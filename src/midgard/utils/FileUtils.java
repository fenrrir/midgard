/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.utils;

import com.sun.squawk.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author fenrrir
 */
public class FileUtils {
    
    public static String readFile(String filename) {
    InputStream is = null;
    BufferedReader br = null;
    String line;
    StringBuffer content = new StringBuffer();

    try {
      is = FileUtils.class.getResourceAsStream(filename);
      br = new BufferedReader(new InputStreamReader(is));
      while (null != (line = br.readLine())) {
         content.append(line);
         content.append("\n");
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      try {
        if (br != null) br.close();
        if (is != null) is.close();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    return content.toString();
  }

}
