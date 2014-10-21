package cn.whm.rest.handler.REST.processor;

import cn.whm.rest.handler.ParamInfo;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by wanghm on 2014/10/20.
 */
public abstract class AbstractProcessor implements ParamProcessor{

    protected boolean doValidate(ParamInfo pi,String value) throws Exception{
        if(pi.isRequired()){
            if(StringUtils.isEmpty(value) || pi.getDefaultValue() == null){
                throw new Exception("参数"+pi.getValue()+"不能为空!");
            }
        }
        return true;
    }
    @Override
    public abstract Object processParam(ParamInfo pi,String value) throws Exception;
}
