package cn.whm.amqp.dispatcher;

import cn.whm.amqp.core.MessageRouterExchange;
import cn.whm.amqp.interfaces.DispatcherExchange;
import cn.whm.amqp.interfaces.MessageRoute;
import cn.whm.utils.SpringUtilsContext;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wanghm on 2014/10/13.
 */
public class AMQPDispatcher {
    private static Logger logger = LoggerFactory.getLogger(AMQPDispatcher.class);

    private ConnectionFactory connectionFactory;

    private ExecutorService executorService;

    private Connection connection;

    private List<Channel> channelList;

    private List<DispatcherExchange> dispatcherExchanges;

    private int concurrentAMQP;

    private int concurrentThread;

    public AMQPDispatcher(){

    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void setConcurrentThread(int concurrentThread) {
        this.concurrentThread = concurrentThread;
    }

    public void setConcurrentAMQP(int concurrentAMQP) {
        this.concurrentAMQP = concurrentAMQP;
    }

    public void setDispatcherExchanges(List<DispatcherExchange> dispatcherExchanges) {
        this.dispatcherExchanges = dispatcherExchanges;
    }

    public void init() throws IOException {
        logger.info("starting init MessageDispatcher....");
        executorService = Executors.newFixedThreadPool(concurrentThread);
        connection = connectionFactory.newConnection(executorService);
        channelList = new ArrayList<Channel>();
        for(int i = 0;i<this.concurrentAMQP;i++){
            Channel channel = connection.createChannel();

        }

    }

    private void configAMQPChannel(Channel channel) throws IOException {
        configExchange(channel);
        Reflections reflections = SpringUtilsContext.getBean("reflections",Reflections.class);
        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(MessageRoute.class);
        for(Class clazz : classSet){
            Annotation[] annotations = clazz.getAnnotations();
            for(Annotation annotation : annotations){
                if(annotation instanceof MessageRoute){
                    registerMessageCmdlet(channel,clazz.getName(),(MessageRoute)annotation);
                }
            }
        }
    }

    private void registerMessageCmdlet(Channel channel,String className,MessageRoute messageRoute) throws IOException {
         String queneName = "quene."+messageRoute.routeKey();

        logger.debug("Attacking message RouteKey {} with cmdlet: {}",messageRoute.routeKey(),className);

        channel.queueDeclare(queneName,false,false,true,null);
        channel.queueBind(queneName,messageRoute.SourceExchange(),messageRoute.routeKey());

        AbstractMessageCmdlet cmdlet = (AbstractMessageCmdlet)SpringUtilsContext.getBean(className);
        if(messageRoute.ConsumerCount()>0){
            cmdlet.setConsumerCount(messageRoute.ConsumerCount());
        }else{
            throw new InvalidParameterException("ConsumerCount should not be negative!");
        }
        cmdlet.setChannel(channel);

        List<AbstractMessageCmdlet.MessageConsumer> consumerList = cmdlet.callback();
        for(AbstractMessageCmdlet.MessageConsumer messageConsumer : consumerList){
            channel.basicConsume(queneName,messageConsumer);
        }
    }

    private void configExchange(Channel channel) throws IOException {
        MessageRouterExchange routerExchange = new MessageRouterExchange();
        channel.exchangeDeclare(routerExchange.getName(),routerExchange.getType(),
                routerExchange.isDurable(),routerExchange.isAutoDelete(),null);
    }
}
