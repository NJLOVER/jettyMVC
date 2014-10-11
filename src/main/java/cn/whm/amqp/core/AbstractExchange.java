package cn.whm.amqp.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanghm on 2014/10/11.
 */
public abstract class AbstractExchange implements Exchange {

    private final String name;

    private final boolean durable;

    private final boolean autoDelete;

    private final Map<String, Object> arguments;

    /**
     * Construct a new Exchange for bean usage.
     * @param name the name of the exchange.
     */
    public AbstractExchange(String name) {
        this(name, true, false);
    }

    /**
     * Construct a new Exchange, given a name, durability flag, auto-delete flag.
     * @param name the name of the exchange.
     * @param durable true if we are declaring a durable exchange (the exchange will survive a server restart)
     * @param autoDelete true if the server should delete the exchange when it is no longer in use
     */
    public AbstractExchange(String name, boolean durable, boolean autoDelete) {
        this(name, durable, autoDelete, null);
    }

    /**
     * Construct a new Exchange, given a name, durability flag, and auto-delete flag, and arguments.
     * @param name the name of the exchange.
     * @param durable true if we are declaring a durable exchange (the exchange will survive a server restart)
     * @param autoDelete true if the server should delete the exchange when it is no longer in use
     * @param arguments the arguments used to declare the exchange
     */

    public AbstractExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments){
        super();
        this.name = name;
        this.durable = durable;
        this.autoDelete = autoDelete;
        if(null != arguments){
            this.arguments = arguments;
        }else{
            this.arguments = new HashMap<String,Object>();
        }
    }

    public abstract String getType();

    public String getName() {
        return name;
    }

    public boolean isDurable() {
        return durable;
    }

    public boolean isAutoDelete() {
        return autoDelete;
    }

    protected synchronized void addArgument(String argName, Object argValue) {
        this.arguments.put(argName, argValue);
    }

    public Map<String, Object> getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return "Exchange [name=" + name + ", type=" + this.getType() + ", durable=" + durable + ", autoDelete="
                + autoDelete + ", arguments=" + arguments + "]";
    }
}
