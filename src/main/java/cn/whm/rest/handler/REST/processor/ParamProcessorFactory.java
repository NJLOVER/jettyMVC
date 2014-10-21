package cn.whm.rest.handler.REST.processor;

import cn.whm.rest.handler.ParamInfo;
import cn.whm.rest.handler.REST.RequestParamType;

import java.util.HashMap;

/**
 * Created by wanghm on 2014/10/21.
 */
public class ParamProcessorFactory {
    private static HashMap<RequestParamType, ParamProcessor> processorMap;

    static
    {
        processorMap = new HashMap<RequestParamType, ParamProcessor>();
        processorMap.put(RequestParamType.Integer, new IntegerProcessor());
        processorMap.put(RequestParamType.String, new StringProcessor());
        processorMap.put(RequestParamType.Object, new ObjectProcessor());
        processorMap.put(RequestParamType.Date, new DateProcessor());
        processorMap.put(RequestParamType.BigDecimal, new BigDecimalProcessor());
    }

    public static ParamProcessor getParamProcessor(ParamInfo pi) throws Exception {
        if (processorMap.containsKey(pi.getRequestParamType()))
        {
            return processorMap.get(pi.getRequestParamType());
        }
        else
        {
            throw new Exception("Unsupported parameter processor: "
                    + pi.getRequestParamType().toString());
        }
    }
}
