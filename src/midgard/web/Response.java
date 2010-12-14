/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package midgard.web;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 *
 * @author fenrrir
 */


/**
 * HTTP response.
 * Return one of these from serve().
 */
public class Response {

    /**
     * Default constructor: response = HTTP_OK, data = mime = 'null'
     */
    public Response() {
        this.status = NanoHttp.HTTP_OK;
    }

    /**
     * Basic constructor.
     */
    public Response( String status, String mimeType, InputStream data ) {
        this.status = status;
        this.mimeType = mimeType;
        this.data = data;
    }

    /**
     * Basic constructor. Use when content length is known.
     */
    public Response( String status, String mimeType, InputStream data, int contentLength ) {
        this.status = status;
        this.mimeType = mimeType;
        this.data = data;
        this.contentLength = contentLength;
    }

    /**
     * Convenience method that makes an InputStream out of
     * given text.
     */
    public Response( String status, String mimeType, String txt ) {
        this(status, mimeType, new ByteArrayInputStream(txt.getBytes()), txt.length());
    }

    /**
     * Adds given line to the header.
     */
    public void addHeader( String name, String value ) {
        header.put( name, value );
    }

    /**
     * HTTP status code after processing, e.g. "200 OK", HTTP_OK
     */
    public String status;

    /**
     * MIME type of content, e.g. "text/html"
     */
    public String mimeType;

    /**
     * Content length of the response or -1 if now known.
     */
    public int contentLength = -1;

    /**
     * Data of the response, may be null.
     */
    public InputStream data;

    /**
     * Headers for the HTTP response. Use addHeader()
     * to add lines.
     */
    public Properties header = new Properties();

    public String uri; // for pubsubhubbub
}

