package cn.whm.amqp.core;

import java.util.Map;

/**
 * Created by wanghm on 2014/10/13.
 */
public class MessageRouterExchange extends TopicExchange {
    public MessageRouterExchange() {
        super("message_router",true,false,null);
    }
}
