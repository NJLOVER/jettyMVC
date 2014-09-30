package cn.whm.com.server;

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

/**
 * Created by wanghm on 2014/9/28.
 */
public class TestServerStart {
    private static Logger logger = LoggerFactory.getLogger(TestServerStart.class);
    public static void main(String[] args) {
        Server httpServer = new Server();
        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setMaxThreads(100);
        threadPool.setMinThreads(10);
        httpServer.setThreadPool(threadPool);
        SelectChannelConnector selectChannelConnector = new SelectChannelConnector();
        selectChannelConnector.setPort(8080);
        selectChannelConnector.setHost("");
        Connector[] connectors = {selectChannelConnector};
        httpServer.setConnectors(connectors);
        HandlerList handlerList = new HandlerList();
        DefaultHandler defaultHandler = new DefaultHandler();
        handlerList.addHandler(defaultHandler);
        httpServer.setHandler(handlerList);
        try {
            httpServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("service Started");
    }
}
