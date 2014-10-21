package cn.whm.rest.handler.REST.processor;

import cn.whm.rest.handler.ParamInfo;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by wanghm on 2014/10/20.
 */
public class IntegerProcessor extends AbstractProcessor{

    @Override
    protected boolean doValidate(ParamInfo pi, String value) throws Exception{
        if(pi.isRequired()){
            if(StringUtils.isEmpty(value) || pi.getDefaultValue() == null){
                throw new Exception("int型参数"+pi.getValue()+"不能为空!");
            }
            int i = Integer.parseInt(value);
            if (pi.isNeedValidate())
            {
                if (i < pi.getMinInt() || i > pi.getMaxInt())
                {
                    throw new Exception("int型参数"+pi.getValue()+"不满足上下限限制!");
                }
            }
        }
        return true;
    }

    @Override
    public Object processParam(ParamInfo pi, String value) throws Exception{
        if(doValidate(pi,value)){

           return  Integer.parseInt(value);
        }
        return null;
    }


}
