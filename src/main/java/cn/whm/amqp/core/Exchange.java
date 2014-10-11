package cn.whm.amqp.core;

import java.util.Map;

/**
 * Created by wanghm on 2014/10/11.
 */
public interface Exchange {
    String getType();

    String getName();

    boolean isDurable();

    boolean isAutoDelete();

    Map<String ,Object> getArguments();
}
