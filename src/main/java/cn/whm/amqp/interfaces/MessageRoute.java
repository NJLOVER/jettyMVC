package cn.whm.amqp.interfaces;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by wanghm on 2014/10/13.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageRoute {
    String routeKey();

    String SourceExchange();

    int ConsumerCount() default 1;
}
