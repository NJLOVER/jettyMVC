package cn.whm.rest.handler.REST.processor;

import cn.whm.rest.handler.ParamInfo;

/**
 * Created by wanghm on 2014/10/21.
 */
public class StringProcessor extends AbstractProcessor{
    @Override
    public Object processParam(ParamInfo pi, String value) throws Exception {
        return value;
    }
}
