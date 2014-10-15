package cn.whm.activiti;

import org.activiti.bpmn.BpmnAutoLayout;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Created by wanghm on 2014/10/15.
 * 通过代码生成activiti的bpmn文件
 */
public class DynamicActivitiProcessTest {
    @Resource(name = "taskService")
    private TaskService taskService;

    @Resource(name = "runtimeService")
    private RuntimeService runtimeService;

    @Resource(name = "repositoryService")
    private RepositoryService repositoryService;

    @Resource(name = "historyService")
    private HistoryService historyService;

    @Resource(name = "identityService")
    private IdentityService identityService;

    public void testDynamicDeploy()throws Exception{
        BpmnModel bpmnModel = new BpmnModel();
        Process process = new Process();
        bpmnModel.addProcess(process);
        process.setId("my-process");

        process.addFlowElement(createStartEvent());
        process.addFlowElement(createUserTask("task1", "First task", "fred"));
        process.addFlowElement(createUserTask("task2", "Second task", "john"));
        process.addFlowElement(createEndEvent());

        process.addFlowElement(createSequenceFlow("start", "task1"));
        process.addFlowElement(createSequenceFlow("task1", "task2"));
        process.addFlowElement(createSequenceFlow("task2", "end"));

        new BpmnAutoLayout(bpmnModel);

        Deployment deployment = repositoryService.createDeployment()
                .addBpmnModel("dynamic-model.bpmn",bpmnModel).name("Dynamic process deployment").deploy();
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("my-process");
        List<Task> tasks = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId()).list();

        Assert.assertEquals(1, tasks.size());
        Assert.assertEquals("First task", tasks.get(0).getName());
        Assert.assertEquals("fred", tasks.get(0).getAssignee());

        InputStream processDiagram = repositoryService.getProcessDiagram(processInstance.getProcessDefinitionId());
        FileUtils.copyInputStreamToFile(processDiagram, new File("target/diagram.png"));

        // 7. Save resulting BPMN xml to a file
        InputStream processBpmn = repositoryService.getResourceAsStream(deployment.getId(), "dynamic-model.bpmn");
        FileUtils.copyInputStreamToFile(processBpmn, new File("target/process.bpmn20.xml"));
    }

    private UserTask createUserTask(String id,String name,String assignee){
        UserTask userTask = new UserTask();
        userTask.setId(id);
        userTask.setName(name);
        userTask.setAssignee(assignee);
        return userTask;
    }
    protected StartEvent createStartEvent() {
        StartEvent startEvent = new StartEvent();
        startEvent.setId("start");
        startEvent.setInitiator("applyUserId");
        startEvent.setFormKey("gift:submitReviewTask");
        return startEvent;
    }
    protected EndEvent createEndEvent() {
        EndEvent endEvent = new EndEvent();
        endEvent.setId("end");
        return endEvent;
    }
    protected SequenceFlow createSequenceFlow(String from, String to) {
        SequenceFlow flow = new SequenceFlow();
        flow.setSourceRef(from);
        flow.setTargetRef(to);
        return flow;
    }
}
