package cn.whm.rest.handler.REST.processor;

import cn.whm.rest.handler.ParamInfo;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * Created by wanghm on 2014/10/21.
 */
public class BigDecimalProcessor extends AbstractProcessor {
    @Override
    public Object processParam(ParamInfo pi, String value) throws Exception {
        if(doValidate(pi,value)){
            if(StringUtils.isEmpty(value)){
                return new BigDecimal((String)pi.getDefaultValue());
            }else{
                return new BigDecimal(value);
            }
        }
        return null;
    }
}
