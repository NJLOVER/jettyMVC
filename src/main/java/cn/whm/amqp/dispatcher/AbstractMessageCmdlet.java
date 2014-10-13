package cn.whm.amqp.dispatcher;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.plugin2.message.Message;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghm on 2014/10/13.
 */
public abstract class AbstractMessageCmdlet {
    private Logger logger = LoggerFactory.getLogger(AbstractMessageCmdlet.class);

    private List<MessageConsumer> consumerList;

    private int consumerCount;

    public void setConsumerCount(int consumerCount) {
        this.consumerCount = consumerCount;
    }

    public void setChannel(Channel channel){
        consumerList = new ArrayList<MessageConsumer>(consumerCount);
        for(int i =0;i<consumerCount;i++){
            MessageConsumer messageConsumer = new MessageConsumer(channel);
            consumerList.add(messageConsumer);
        }
    }
    public List<MessageConsumer> callback(){
        return consumerList;
    }

    public class MessageConsumer extends DefaultConsumer{
        private Channel mychannel;
        /**
         * Constructs a new instance and records its association to the passed-in channel.
         *
         * @param channel the channel to which this consumer is attached
         */
        public MessageConsumer(Channel channel) {
            super(channel);
            mychannel = channel;
        }
        @Override
        public void handleDelivery(String consumerTag,
                                   Envelope envelope,
                                   AMQP.BasicProperties properties,
                                   byte[] body) throws IOException {
            String msg = new String(body,"utf-8");
            boolean result = processMessage(consumerTag,envelope,msg);
            if (result) {
                mychannel.basicAck(envelope.getDeliveryTag(), false);
            }
        }
    }

    public abstract boolean processMessage(String tag, Envelope env, String msg);
}
