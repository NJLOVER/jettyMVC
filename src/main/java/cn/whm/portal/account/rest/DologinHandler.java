package cn.whm.portal.account.rest;

import cn.whm.activiti.WorkFlowUtils;
import cn.whm.rest.handler.AbstractRESTCmdlet;
import cn.whm.rest.RESTAnnotation;
import cn.whm.rest.result.JsonRESTResult;
import cn.whm.utils.SpringContextUtils;
import org.activiti.engine.task.Task;
import org.eclipse.jetty.http.HttpMethods;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wanghm on 2014/10/11.
 */
@Controller
@Scope(value="prototype")
public class DologinHandler extends AbstractRESTCmdlet{
    Logger logger = LoggerFactory.getLogger(DologinHandler.class);
    @Resource
    private WorkFlowUtils workFlowUtils;
    private String taskId;
    private String processInstId="";
    @RESTAnnotation(URL="/dologin", Methods= HttpMethods.GET)
    public JsonRESTResult doLogin(Request request) throws Exception{

//        DynamicActivitiProcessTest dynamicActivitiProcessTest = (DynamicActivitiProcessTest)SpringContextUtils.getBean("bpmnTest");
//        dynamicActivitiProcessTest.testDynamicDeploy();
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

    /*
    * 初审节点设置了候选人"firstDepositCheckers",
    */
    @RESTAnnotation(URL="/getfirst", Methods= HttpMethods.GET)
    public JsonRESTResult getFirst(Request request) throws Exception{
        List<Task> tasks = workFlowUtils.getTaskByCandidateGroup("depositflow","secondDepositCheckers");
        for(Task task : tasks){
            logger.error(task.toString());
        }
        JsonRESTResult jsonRESTResult = new JsonRESTResult();
        jsonRESTResult.setStatusCode(800);
        return  jsonRESTResult;
    }
    @RESTAnnotation(URL="/firstCheck", Methods= HttpMethods.GET)
    public JsonRESTResult firstCheck(Request request) throws Exception{
        String assignee="whm";
        String comment = "ok";
        Map<String, Object> valiables = new HashMap<String, Object>();
        valiables.put("firstCheckAgree",true);
        taskId = "206";
        processInstId = "201";
//        workFlowUtils.setAssignee(taskId,assignee);
        workFlowUtils.completeTask(taskId,valiables);
        List<Task> tasks = workFlowUtils.getTaskByCandidateGroup("depositflow","firstDepositCheckers");
        for(Task task : tasks){
            logger.error(task.toString());
        }
        JsonRESTResult jsonRESTResult = new JsonRESTResult();
        jsonRESTResult.setStatusCode(800);
        return  jsonRESTResult;
    }

    @RESTAnnotation(URL="/allTest", Methods= HttpMethods.GET)
    public JsonRESTResult allTest(Request request) throws Exception{
        //1.启动一个提现流程,进入初审环境
        Map map = new HashMap();
        map.put("test","test");
        String proccInstId = workFlowUtils.startProcess("depositflow","wanghm",map);
        //2.查询初审人员所能见的所有流程.
        List<Task> tasks = workFlowUtils.getTaskByCandidateGroup("depositflow","firstDepositCheckers");
        for(Task task : tasks){
            logger.error(task.toString());
            taskId = task.getId();
        }
        //3.初审通过,包括 审核人员签收初审任务,以及审核完成推动流程.
        String assignee="whm";
        String comment = "ok";
        Map<String, Object> valiables = new HashMap<String, Object>();
        valiables.put("firstCheckAgree",true);
        processInstId = proccInstId;
        workFlowUtils.setAssignee(taskId,assignee);
        workFlowUtils.setConmment(taskId,processInstId,"true",comment);
        workFlowUtils.completeTask(taskId,valiables);
        //4 查询复审人员所能见的所有流程
        List<Task> task2s = workFlowUtils.getTaskByCandidateGroup("depositflow","secondDepositCheckers");
        for(Task task : tasks){
            logger.error(task.toString());
            taskId = task.getId();
        }
        //5.xxx不写了,大概差不多.

        JsonRESTResult jsonRESTResult = new JsonRESTResult();
        jsonRESTResult.setStatusCode(800);
        return  jsonRESTResult;
    }
}
