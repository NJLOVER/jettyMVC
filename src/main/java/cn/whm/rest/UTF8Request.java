package cn.whm.rest;

import org.eclipse.jetty.server.AbstractHttpConnection;
import org.eclipse.jetty.server.Request;

import java.io.UnsupportedEncodingException;

/**
 * Created by wanghm on 2014/10/9.
 */
public class UTF8Request extends Request {
    public UTF8Request(AbstractHttpConnection connection) {
        super(connection);
    }

    public UTF8Request(Request baseRequest) throws UnsupportedEncodingException {
        super(baseRequest.getConnection());
        this.setAttributes(baseRequest.getAttributes());
        this.setCharacterEncoding(baseRequest.getCharacterEncoding());
        this.setDispatcherType(baseRequest.getDispatcherType());
        this.setMethod(baseRequest.getMethod());
        this.setParameters(baseRequest.getParameters());
        this.setUri(baseRequest.getUri());
    }

    @Override
    public String getHeader(String field) {
        String s = super.getHeader(field);
        try {
            s = new String(s.getBytes("ISO-8859-1"), "utf8");
        } catch (Exception ex) {

        }
        return s;
    }
}
