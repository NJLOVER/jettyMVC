package cn.whm.rest.result;

import java.io.StringWriter;

/**
 * Created by wanghm on 2014/9/30.
 */
public class PlainTextRESTResult extends AbstractRESTResult{
    private StringWriter outputWrite;

    public PlainTextRESTResult(){
        outputWrite = new StringWriter(1024);
    }

    public StringWriter getWriter(){
        return outputWrite;
    }

    @Override
    public String toHttpResponse() {
        return outputWrite.toString();
    }
}
