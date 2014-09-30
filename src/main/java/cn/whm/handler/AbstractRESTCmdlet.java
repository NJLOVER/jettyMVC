package cn.whm.handler;

import cn.whm.result.AbstractRESTResult;
import org.eclipse.jetty.server.Request;

/**
 * Created by wanghm on 2014/9/30.
 */
public abstract class AbstractRESTCmdlet {
    public abstract AbstractRESTResult execute(Request request);
}
