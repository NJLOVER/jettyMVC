package cn.whm.com.server;

import cn.whm.amqp.publisher.MessagePublisher;
import cn.whm.handler.DispathRestFullHandler;
import com.alibaba.fastjson.JSONObject;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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




        //MessagePublisher messagePublisher = (MessagePublisher)applicationContext.getBean("amqp_publisher");
        //rabbitmq的发送测试,需要先在rabbitmq服务器上配置一个
        // message_router的exchange和一个叫queue.debug.test的queue,
        //同时绑定路由-->debug.test,也可以考虑在项目启动时初始化这些配置,
        // 代码应该写在messagePublisher.init()方法中.
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
