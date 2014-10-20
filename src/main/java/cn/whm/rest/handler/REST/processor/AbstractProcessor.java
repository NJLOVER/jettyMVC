package cn.whm.rest.handler.REST.processor;

import cn.whm.rest.handler.ParamInfo;

/**
 * Created by wanghm on 2014/10/20.
 */
public abstract class AbstractProcessor implements ParamProcessor{

    protected abstract boolean doValidate(ParamInfo pi,String value) throws Exception;
    @Override
    public abstract Object processParam(ParamInfo pi,String value) throws Exception;
}
