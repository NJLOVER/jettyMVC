package cn.whm.rest.annotate;



import cn.whm.rest.handler.REST.RequestParamType;
import cn.whm.rest.handler.REST.RequestSource;

import java.lang.annotation.*;

/**
 * Description：
 * Author: ChenLong
 * Date: 14-3-19
 * Time: 下午3:27
 */
@Target({ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface StringParam {
    String value();
    RequestSource source() default RequestSource.HeaderParam;
    RequestParamType paramType() default RequestParamType.String;
    String defaultValue() default "";
    boolean required() default true;
    boolean validated() default false;
    int maxLength() default Integer.MAX_VALUE;
}
