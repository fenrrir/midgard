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
package midgard.config;

import midgard.componentmodel.Component;
import midgard.kernel.Midgard;
import midgard.utils.FileUtils;
import midgard.web.json.JSONException;
import midgard.web.json.JSONObject;

/**
 *
 * @author fenrrir
 */
public class DefaultDefaultConfig extends Component implements IDefaultConfig { //DefaultConfigImpl

    private JSONObject json = null;
    private final String REPO = "/defaultConf.json";

    public void initialize() {
        super.initialize();
        open();       
    }

    public void destroy() {
        pause();
        super.destroy();
    }

    public void pause() {
        super.pause();
        json = null;
    }

    public void resume() {
        super.resume();
        open();
    }

    public long getSleepTime() {
        try {
            return json.getLong("sleep");
        } catch (JSONException ex) {
            ex.printStackTrace();
            return 5000;
        }
    }

    private void open() {
        try {
            json = new JSONObject(FileUtils.readFile(REPO));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    public String getAdaptationProfileRepositoryPath() {
        return getSystemProperty("AdaptationProfileRepositoryPath");
    }

    public String getAppRepositoryPath() {
        return getSystemProperty("AppRepositoryPath");
    }

    public String getSystemProperty(String name) {
        return Midgard.getProperty(name);
    }


}
