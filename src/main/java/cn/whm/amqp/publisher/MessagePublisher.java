package cn.whm.amqp.publisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;

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


}
