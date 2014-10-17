package cn.whm.activiti;

import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by wanghm on 2014/10/16.
 */
public class WorkFlowUtils {
    @Resource(name = "taskService")
    private TaskService taskService;

    @Resource(name = "runtimeService")
    private RuntimeService runtimeService;

    @Resource(name = "repositoryService")
    private RepositoryService repositoryService;

    @Resource(name = "historyService")
    private  HistoryService historyService;

    @Resource(name = "identityService")
    private IdentityService identityService;

    //加载一个bpmn流程文件
    public void addDeploy(String filePath){
        repositoryService.createDeployment().addClasspathResource(filePath).deploy();
    }

    public String startProcess(String processKey,String userId,Map<String,Object> processDate ){
        if(StringUtils.isNotEmpty(userId)){
            serAuthor(userId);
        }
        ProcessInstance pinst = runtimeService.startProcessInstanceByKey(processKey,processDate);
        return pinst.getId();
    }

    public ProcessInstance startProcessInstance(String procKey,String bussinessKey,Map<String,Object> procMap){
        return runtimeService.startProcessInstanceByKey(procKey,bussinessKey,procMap);
    }

    public ProcessInstance getProcInstByBussinessId(String bussinessId){
        return runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(bussinessId).singleResult();
    }

    public void cancelProcess(String processId){
        ProcessInstance p = getProcessInstanceByID(processId);
        runtimeService.deleteProcessInstance(p.getProcessInstanceId(),"system cancel");
    }

    private ProcessInstance getProcessInstanceByID(String processInstId) {
        ProcessInstance p = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstId).singleResult();
        return p;
    }

    //查询该流程实例下的所有task
    public List<Task> getAllTaskByProcInstId(String procInstId){
        return taskService.createTaskQuery().processInstanceId(procInstId).list();
    }

    //通过流程发起人Id查询所有激活状态的task
    public List<Task> getActiveTaskByAssignee(String definitionKey,String userId){
        return taskService.createTaskQuery().processDefinitionKey(definitionKey).taskAssignee(userId)
                .active().orderByTaskId().desc().orderByTaskCreateTime().desc().list();
    }
    //通过候选组id查询所拥有任务(单一流程)
    public List<Task> getTaskByCandidateGroup(String definitionKey,String groupId){
        return taskService.createTaskQuery().processDefinitionKey(definitionKey)
                .taskCandidateGroup(groupId).list();
    }



    public void serAuthor(String userId){
        identityService.setAuthenticatedUserId(userId);
    }
}
