package cn.whm.rest.handler.REST.processor;

import cn.whm.rest.handler.ParamInfo;

/**
 * Created by wanghm on 2014/10/20.
 */
public interface ParamProcessor {
    Object processParam(ParamInfo pi,String value) throws Exception;
}
