package cn.whm.amqp.interfaces;

import cn.whm.amqp.core.TopicExchange;

import java.util.Map;

/**
 * Created by wanghm on 2014/10/13.
 */
public class DispatcherExchange extends TopicExchange {

    public DispatcherExchange(String name) {
        super(name);
    }

    public DispatcherExchange(String name, boolean durable, boolean aotuDelete) {
        super(name, durable, aotuDelete);
    }

    public DispatcherExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments) {
        super(name, durable, autoDelete, arguments);
    }

    private String routeKey;

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }
}
