package cn.whm.rest.annotate;



import cn.whm.rest.handler.REST.RequestParamType;
import cn.whm.rest.handler.REST.RequestSource;

import java.lang.annotation.*;

/**
 * Description：
 * Author: ChenLong
 * Date: 14-3-19
 * Time: 下午3:28
 */
@Target({ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface IntParam {
    String value();
    RequestSource source() default RequestSource.HeaderParam;
    RequestParamType paramType() default RequestParamType.Integer;
    int defaultValue() default 0;
    boolean required() default true;
    boolean validated() default false;
    int min() default Integer.MIN_VALUE;
    int max() default Integer.MAX_VALUE;
}
