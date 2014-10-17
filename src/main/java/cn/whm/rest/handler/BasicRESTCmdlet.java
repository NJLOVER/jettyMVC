package cn.whm.rest.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghm on 2014/10/17.
 */
public class BasicRESTCmdlet extends AbstractRESTCmdlet {

    public void PreconfigureHooker(){

    }

    private List<ParamInfo> analyseRESTRequestParam(Method method) throws Exception{
        List<ParamInfo> paramInfoList = new ArrayList<ParamInfo>();
        Class<?>[] params = method.getParameterTypes();

        Annotation[][] annotations = method.getParameterAnnotations();
        for(Annotation[] anns : annotations){
            for(Annotation ann : anns){

            }
        }
        return paramInfoList;
    }
}
