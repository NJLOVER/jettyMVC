package cn.whm.amqp.core;

import java.util.Map;

/**
 * Created by wanghm on 2014/10/11.
 */
public class TopicExchange extends AbstractExchange {
    public TopicExchange(String name){
        super(name);
    }
    public TopicExchange(String name,boolean durable,boolean aotuDelete){
        super(name,durable,aotuDelete);
    }
    public TopicExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments) {
        super(name, durable, autoDelete, arguments);
    }

    @Override
    public String getType() {
        return ExchangeType.TOPIC;
    }
}
