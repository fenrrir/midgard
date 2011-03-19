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
