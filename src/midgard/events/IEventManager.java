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

package midgard.events;

import java.util.Vector;
import midgard.services.IService;

/**
 *
 * @author fenrrir
 */
public interface IEventManager extends IService {
    public Vector listAllEvents();
    public Vector listAllCustomEvents();
    public Vector listInstalledCustomEvents();
    public ICustomEvent getCustomEvent(String name);
    public IEvent getEvent(String name);
    public void loadAndInitializeCustomEvent(String name);
    public void destroyCustomEvent(String name);
    public void destroyCustomEvent(ICustomEvent event);
    public void resumeCustomEvent(String name);
    public void resumeCustomEvent(ICustomEvent event);
    public void pauseCustomEvent(String name);
    public void pauseCustomEvent(ICustomEvent event);
}
