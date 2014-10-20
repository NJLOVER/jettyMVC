package cn.whm.rest.handler.REST.processor;

import cn.whm.rest.handler.ParamInfo;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wanghm on 2014/10/20.
 */
public class DateProcessor extends AbstractProcessor {
    @Override
    protected boolean doValidate(ParamInfo pi, String value) throws Exception {
        if(pi.isRequired()){
           if(StringUtils.isEmpty(value) || null == pi.getDefaultValue()){
               throw new Exception("Date型参数"+pi.getValue()+"不能为空!");
           }
        }
        return true;
    }

    @Override
    public Object processParam(ParamInfo pi, String value) throws Exception {
        if(doValidate(pi,value)){
            if (StringUtils.isEmpty(value))
            {
                return pi.getDefaultValue();
            }
            switch (pi.getRequestDateFormat())
            {
                case LongFormat:
                    Long l = Long.parseLong(value);
                    return new Date(l);
                case StringFormat:
                    DateFormat df = new SimpleDateFormat();
                    return df.parse(value);
            }
        }
        return null;
    }
}
