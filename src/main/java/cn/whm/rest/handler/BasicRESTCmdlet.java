package cn.whm.rest.handler;

import cn.whm.rest.handler.REST.ParamInfoUtils;
import cn.whm.rest.handler.REST.RequestSource;
import cn.whm.rest.handler.REST.exception.InvalidReqParamException;
import cn.whm.rest.handler.REST.processor.ParamProcessor;
import cn.whm.rest.handler.REST.processor.ParamProcessorFactory;
import cn.whm.rest.result.AbstractRESTResult;
import cn.whm.rest.result.JsonRESTResult;
import cn.whm.rest.result.ResultConst;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghm on 2014/10/17.
 */
public class BasicRESTCmdlet extends AbstractRESTCmdlet {
    private Logger logger = LoggerFactory.getLogger(BasicRESTCmdlet.class);
    private List<ParamInfo> paramInfoList;

    public void PreconfigureHooker() throws Exception{
        paramInfoList = analyseRESTRequestParam(this.getMethod());

    }

    private List<ParamInfo> analyseRESTRequestParam(Method method) throws Exception{
        List<ParamInfo> paramInfoList = new ArrayList<ParamInfo>();
        Annotation[][] annotations = method.getParameterAnnotations();
        for(Annotation[] anns : annotations){
            for(Annotation ann : anns){
                ParamInfo paramInfo = ParamInfoUtils.parseParamInfo(ann);
                paramInfoList.add(paramInfo);
            }
        }
        return paramInfoList;
    }
    public AbstractRESTResult execute(Request request) {
        JsonRESTResult result = new JsonRESTResult();
        try{
            logRequestParam(request);
            return processRequest(request,result);
        }catch (Exception e){
            result.setStatusCode(ResultConst.ERROR.getValue());
            result.setMsg(e.getMessage());
        }
        return result;
    }

    private AbstractRESTResult processRequest(Request request,JsonRESTResult restResult) throws Exception {
        List<Object> param_list = new ArrayList<Object>();

        for(ParamInfo paramInfo : paramInfoList){
            ParamProcessor processor = ParamProcessorFactory.getParamProcessor(paramInfo);
            Object param = processor.processParam(paramInfo,this.getReqValue(request,paramInfo));

            param_list.add(param);
        }
        Object returnObj = MethodUtils.invokeMethod(this,this.getMethod().getName(),param_list.toArray());
        if(returnObj instanceof AbstractRESTResult){
            return (AbstractRESTResult)returnObj;
        }else if( returnObj != null){
            restResult.setReturnObj(returnObj);
            restResult.setStatusCode(ResultConst.SUCESS.getValue());
        }else{
            restResult.setStatusCode(ResultConst.FAIL.getValue());
            restResult.setMsg("nothing return!");
        }
        return restResult;
    }

    private void logRequestParam(Request request) throws InvalidReqParamException{
        logger.info("Paramters:");
        for(ParamInfo param : paramInfoList){
            String value =getReqValue(request,param);
        }
    }

    private String getReqValue(Request request,ParamInfo paramInfo) throws InvalidReqParamException{
        String value;
        switch(paramInfo.getRequestSource()){
            case HeaderParam:
                value = request.getHeader(paramInfo.getValue());
                break;
            case BodyParam:
                value = request.getParameter(paramInfo.getValue());
                break;
            default:
                throw new InvalidReqParamException("Invalid request parameter type!");
        }
        if(StringUtils.isEmpty(value)){
            value = "";
        }
        return value;
    }
}
