package cn.whm.activiti;

import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.task.Comment;
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
    //启动一个流程实例,并记录发起人ID.
    public String startProcess(String processKey,String userId,Map<String,Object> processDate ){
        if(StringUtils.isNotEmpty(userId)){
            setAuthor(userId);
        }
        ProcessInstance pinst = runtimeService.startProcessInstanceByKey(processKey,processDate);
        clearAuthor();
        return pinst.getId();
    }
    //发起一个流程,并记录业务Id.
    public ProcessInstance startProcessInstance(String procKey,String bussinessKey,Map<String,Object> procMap){
        return runtimeService.startProcessInstanceByKey(procKey,bussinessKey,procMap);
    }
    //通过发起流程的业务ID返回业务流程.
    public ProcessInstance getProcInstByBussinessId(String bussinessId){
        return runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(bussinessId).singleResult();
    }
    //删除流程实例
    public void cancelProcessAndHis(String processInstId){
        runtimeService.deleteProcessInstance(processInstId,"system cancel");
        historyService.deleteHistoricProcessInstance(processInstId);
    }
    //同过流程实例ID查询流程实例信息
    private ProcessInstance getProcessInstanceByID(String processInstId) {
        ProcessInstance p = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstId).singleResult();
        return p;
    }

    //查询该流程实例下的所有task
    public List<Task> getAllTaskByProcInstId(String procInstId){
        return taskService.createTaskQuery().processInstanceId(procInstId).list();
    }

    //通过流程签收人Id查询所签收的task
    public List<Task> getActiveTaskByAssignee(String definitionKey,String userId){
        return taskService.createTaskQuery().processDefinitionKey(definitionKey).taskAssignee(userId)
                .active().orderByTaskId().desc().orderByTaskCreateTime().desc().list();
    }
    //设置签收人
    public void setAssignee(String taskId,String assignee){
        taskService.claim(taskId,assignee);
    }

    //通过候选组id查询未签收任务(单一流程)
    public List<Task> getTaskByCandidateGroup(String definitionKey,String groupId){
        return taskService.createTaskQuery().processDefinitionKey(definitionKey)
                .taskCandidateGroup(groupId).list();
    }
    //有网关的用户任务完成.
    public void completeTask(String taskId,Map<String, Object> variables ){
        taskService.complete(taskId,variables);
    }

    //设置流程发起人.
    public void setAuthor(String userId){
        identityService.setAuthenticatedUserId(userId);
    }
    //清空流程发起人
    public void clearAuthor(){
        identityService.setAuthenticatedUserId(null);
    }

    //给任务添加附件信息
    public void setAttach(String attachName,String taskId,String processInstanceId,String name,String description,String url){
        Attachment attachment = taskService.createAttachment("comment", taskId, processInstanceId, name, description, url);
        taskService.saveAttachment(attachment);
    }

    //添加审核意见,type可以保存是否通过,msg可以保存原因等等
    public void setConmment(String taskId,String processInstId,String type,String msg){
        taskService.addComment(taskId,processInstId,type,msg);
    }
    //获取整个流程实例的审核意见
    public List<Comment> getAllComment(String procInstId){
        List<Comment> comments = taskService.getProcessInstanceComments(procInstId);
        return comments;
    }
}
