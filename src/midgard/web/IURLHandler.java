/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
