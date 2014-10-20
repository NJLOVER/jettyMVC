package cn.whm.rest.annotate;



import cn.whm.rest.handler.REST.RequestParamType;
import cn.whm.rest.handler.REST.RequestSource;

import java.lang.annotation.*;

/**
 * Description：
 * Author: ChenLong
 * Date: 14-3-19
 * Time: 下午5:52
 */
@Target({ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface TypedParam {
    String value();
    RequestSource source() default RequestSource.BodyParam;
    RequestParamType paramType() default RequestParamType.Object;
    boolean required() default true;
    boolean validated() default false;
    Class type() default Object.class;
}
