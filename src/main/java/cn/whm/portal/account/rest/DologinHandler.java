package cn.whm.portal.account.rest;

import cn.whm.rest.handler.AbstractRESTCmdlet;
import cn.whm.rest.RESTAnnotation;
import cn.whm.rest.result.JsonRESTResult;
import org.eclipse.jetty.http.HttpMethods;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * Created by wanghm on 2014/10/11.
 */
@Controller
public class DologinHandler extends AbstractRESTCmdlet{
    Logger logger = LoggerFactory.getLogger(DologinHandler.class);
    @RESTAnnotation(URL="/dologin", Methods= HttpMethods.GET)
    public JsonRESTResult doLogin(Request request){
        JsonRESTResult jsonRESTResult = new JsonRESTResult();
        jsonRESTResult.setReturnObj("123");
        jsonRESTResult.setStatusCode(800);
        jsonRESTResult.setMsg("login seccuss");
        return  jsonRESTResult;
    }
}
