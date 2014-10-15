package cn.whm.com.server;

import cn.whm.activiti.DynamicActivitiProcessTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * Created by wanghm on 2014/9/28.
 */
public class TestServerStart {
    private static Logger logger = LoggerFactory.getLogger(TestServerStart.class);
    public static void main(String[] args) throws Exception{
        //启动spring框架.
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
        applicationContext.getClass();
        logger.info("service Started");

        DynamicActivitiProcessTest dynamicActivitiProcessTest = (DynamicActivitiProcessTest)applicationContext.getBean("bpmnTest");
        dynamicActivitiProcessTest.testDynamicDeploy();
        //EmailSendUtils sendUtils = (EmailSendUtils)applicationContext.getBean("mailUtils");
        //sendUtils.sendMail("test","test","459519543@qq.com");

        //MessagePublisher messagePublisher = (MessagePublisher)applicationContext.getBean("amqp_publisher");
        //rabbitmq的发送测试,需要先在rabbitmq服务器上配置一个
        // message_router的exchange和一个叫queue.debug.test的queue,
        //同时绑定路由-->debug.test,也可以考虑在项目启动时初始化这些配置,
        //代码应该写在messagePublisher.init()方法中.
        //messagePublisher.sendMessage("debug.test","hello");
    }
    public void testExecutorService(){

    }
    public class myThread implements Runnable{

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
