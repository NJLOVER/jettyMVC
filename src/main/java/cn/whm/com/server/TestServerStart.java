package cn.whm.com.server;

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
    }

    public  <T> T getBean(Class<T> pojo) throws ClassNotFoundException {
        return (T)Class.forName(pojo.getName());
    }
}
