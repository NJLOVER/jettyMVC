package cn.whm.amqp.publisher;

import cn.whm.amqp.core.MessageRouterExchange;
import cn.whm.utils.JsonUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wanghm on 2014/10/11.
 */
public class MessagePublisher {
    private Logger logger = LoggerFactory.getLogger(MessagePublisher.class);

    private ConnectionFactory connectionFactory;
    //线程池
    private ExecutorService executorService;

    private Connection connection;

    private Channel channel;

    private int concurrentThread;

    private MessageRouterExchange router_exchange;

    //通过配置文件管理线程数量和mq连接池信息
    public void setConcurrentThread(int concurrentThread) {
        this.concurrentThread = concurrentThread;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public MessagePublisher(){
        logger.info("new MessagePublisher create......");
        router_exchange = new MessageRouterExchange();
    }
    public void init() throws IOException{
        logger.info("starting init MessagePublisher....");
        executorService = Executors.newFixedThreadPool(concurrentThread);
        connection = connectionFactory.newConnection(executorService);

        channel = connection.createChannel();

        channel.exchangeDeclare(router_exchange.getName(),router_exchange.getType(),router_exchange.isDurable(),router_exchange.isAutoDelete(),router_exchange.getArguments());
    }

    public void clean() throws IOException{
        channel.close();
        connection.close();
        executorService.shutdown();
    }

    public boolean sendMessage(String routerKey,Object o) throws IOException{
        return sendMessage(routerKey, JsonUtils.toJson(o));
    }
    public boolean sendMessage(String routerKey,String body){
        return sendMessage(routerKey,body.getBytes());
    }
    public boolean sendMessage(String routerKey,byte[] body){
        try{
            channel.basicPublish(router_exchange.getName(),routerKey,null,body);
            logger.info("sending message:router={},message={}",routerKey,new String(body,"UTF-8"));
            return true;
        }catch (Exception e){
            logger.error("send message false:routingKey={}, message={}, errorInfo={}",routerKey, new String(body),e );
            return false;
        }
    }
}
