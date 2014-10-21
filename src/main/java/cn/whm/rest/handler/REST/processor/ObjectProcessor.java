package cn.whm.rest.handler.REST.processor;

import cn.whm.rest.handler.ParamInfo;
import cn.whm.utils.JsonUtils;

/**
 * Created by wanghm on 2014/10/21.
 */
public class ObjectProcessor extends AbstractProcessor {
    @Override
    public Object processParam(ParamInfo pi, String value) throws Exception {
        String className = ((Class)pi.getParameterType()).getName();
        Object o = JsonUtils.fromJson(value, Class.forName(className));
        return o;
    }
}
