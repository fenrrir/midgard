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

package midgard.adaptation;

import java.util.Vector;
import midgard.componentmodel.IComponent;
import midgard.events.IEventHandler;
import midgard.events.IListener;
import midgard.web.json.JSONObject;

/**
 *
 * @author fenrrir
 */
public interface IAdaptationProfile extends IListener, IEventHandler {
    public void setup();
    public void clear();

    public String getProfileName();
    public void setProfileName(String name);

    public void setConfiguration(JSONObject conf);
    public void setHelper(IAdaptationProfileHelper helper);
    public JSONObject getConfiguration();
    public Vector connectToComponents();
    public Vector connectComponentsToMe();
    public Vector requiredEvents();
}
