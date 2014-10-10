package cn.whm.handler;

import cn.whm.result.AbstractRESTResult;
import org.eclipse.jetty.server.Request;

/**
 * Created by wanghm on 2014/9/30.
 */
public class AbstractRESTCmdlet {
    private String methodName;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
