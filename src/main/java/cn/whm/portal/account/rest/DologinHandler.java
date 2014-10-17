package cn.whm.portal.account.rest;

import cn.whm.activiti.WorkFlowUtils;
import cn.whm.rest.handler.AbstractRESTCmdlet;
import cn.whm.rest.RESTAnnotation;
import cn.whm.rest.result.JsonRESTResult;
import cn.whm.utils.SpringContextUtils;
import org.eclipse.jetty.http.HttpMethods;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanghm on 2014/10/11.
 */
@Controller
@Scope(value="prototype")
public class DologinHandler extends AbstractRESTCmdlet{
    Logger logger = LoggerFactory.getLogger(DologinHandler.class);
    @RESTAnnotation(URL="/dologin", Methods= HttpMethods.GET)
    public JsonRESTResult doLogin(Request request) throws Exception{

//        DynamicActivitiProcessTest dynamicActivitiProcessTest = (DynamicActivitiProcessTest)SpringContextUtils.getBean("bpmnTest");
//        dynamicActivitiProcessTest.testDynamicDeploy();
        WorkFlowUtils workFlowUtils = (WorkFlowUtils)SpringContextUtils.getBean("workFlowUtils");
        Map map = new HashMap();
        map.put("test","test");
        String pinstId = workFlowUtils.startProcess("depositflow","test",map);
        logger.info(pinstId);
        workFlowUtils.getTaskByCandidateGroup("depositflow","firstDepositCheckers");

        JsonRESTResult jsonRESTResult = new JsonRESTResult();
        jsonRESTResult.setReturnObj("123");
        jsonRESTResult.setStatusCode(800);
        jsonRESTResult.setMsg("login seccuss");
        return  jsonRESTResult;
    }
    @RESTAnnotation(URL="/apply", Methods= HttpMethods.GET)
    public JsonRESTResult startTask(Request request) throws Exception{

//        DynamicActivitiProcessTest dynamicActivitiProcessTest = (DynamicActivitiProcessTest)SpringUtilsContext.getBean("bpmnTest");
//        dynamicActivitiProcessTest.testDynamicDeploy();

        JsonRESTResult jsonRESTResult = new JsonRESTResult();
        jsonRESTResult.setReturnObj("123");
        jsonRESTResult.setStatusCode(800);
        jsonRESTResult.setMsg("login seccuss");
        return  jsonRESTResult;
    }
}
