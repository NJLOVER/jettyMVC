package cn.whm.rest;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by wanghm on 2014/10/9.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface RESTAnnotation {
    String URL();

    String Methods();
}
