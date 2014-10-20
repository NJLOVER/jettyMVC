package cn.whm.rest.annotate;



import cn.whm.rest.handler.REST.RequestParamType;
import cn.whm.rest.handler.REST.RequestSource;

import java.lang.annotation.*;

/**
 * Created by chenlong on 14-3-27.
 */
@Target({ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface BigDecimalParam {

    String value();
    RequestSource source() default RequestSource.HeaderParam;
    RequestParamType paramType() default RequestParamType.BigDecimal;
    boolean required() default true;
    boolean validated() default false;

}
