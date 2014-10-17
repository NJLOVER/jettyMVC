package cn.whm.rest.handler;

import cn.whm.rest.UTF8Request;
import cn.whm.rest.result.AbstractRESTResult;
import org.apache.commons.beanutils.MethodUtils;
import org.eclipse.jetty.server.Request;

import java.lang.reflect.Method;

/**
 * Created by wanghm on 2014/9/30.
 */
public class AbstractRESTCmdlet {
    private Method method;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
    public AbstractRESTResult execute(Request request,Method method) throws Exception{
        return (AbstractRESTResult) MethodUtils.invokeMethod(this, method.getName(), request);
    }
    public void PreconfigureHooker(){

    }
}
