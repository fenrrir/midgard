/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package midgard.web;

/**
 *
 * @author fenrrir
 */
public class URLView implements IURLHandler{

    private String uri;
    private IURLHandler handler;

    public String getUri() {
        return uri;
    }

    /**
     * Creates a new application object with the given URI prefix
     * and request handler.
     */
    public URLView(String uri, IURLHandler handler) {
        this.handler = handler;
        this.uri = uri;
    }

    /**
     * Calls the IURLHandler's serve method after stripping the URI
     * prefix.
     */
    public Response serve(Request request) throws Exception {
        return handler.serve(request);
    }
}
