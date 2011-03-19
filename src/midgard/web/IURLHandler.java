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

package midgard.web;

/**
 *
 * @author fenrrir
 */


/**
 * Interface defining an Application for the NanoHTTP server.
 */
public interface IURLHandler {

    /**
     * Processes a HTTP request and generates a response. For convenience, the
     * web server can take care of error messages in the case of exceptions.
     * @param uri Request URI, not including the application specific prefix.
     * @param header HTTP headers
     * @param parms Parameters extracted from the URI and/or the POST request data.
     * @returns HTTP response
     */
    public Response serve(Request request) throws Exception;

}
