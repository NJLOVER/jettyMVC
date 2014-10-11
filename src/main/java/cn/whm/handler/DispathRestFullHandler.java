package cn.whm.handler;

import cn.whm.rest.RESTAnnotation;
import cn.whm.rest.UTF8Request;
import cn.whm.result.AbstractRESTResult;
import cn.whm.result.JsonRESTResult;
import cn.whm.utils.SpringUtilsContext;
import org.apache.commons.beanutils.MethodUtils;
import org.eclipse.jetty.http.HttpMethods;
import org.eclipse.jetty.http.PathMap;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by wanghm on 2014/9/28.
 */
public class DispathRestFullHandler extends AbstractHandler{
    private static Logger logger = LoggerFactory.getLogger(DispathRestFullHandler.class);
    private PathMap PostMap;
    private PathMap GetMap;

    public DispathRestFullHandler(){
        PostMap = new PathMap(100);
        GetMap = new PathMap(100);

        PostMap.put("/*",new DebugRESTCmdlet());
        GetMap.put("/*",new DebugRESTCmdlet());
    }
    public void init() throws Exception{
        scanHandler();
    }

    private void scanHandler() throws Exception{
        Reflections reflections = SpringUtilsContext.getBean("reflections",Reflections.class);
        Set<Class<?>> clazzSet = reflections.getTypesAnnotatedWith(Controller.class);
        for(Class clazz : clazzSet){
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method : methods){
                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof RESTAnnotation) {
                        try {
                            registerCmdlet(clazz,method, (RESTAnnotation) annotation);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private void registerCmdlet(Class clazz,Method method,RESTAnnotation annotation){
        String methods = annotation.Methods();
        String URI = annotation.URL();

        AbstractRESTCmdlet cmdlet = (AbstractRESTCmdlet) SpringUtilsContext.getBeanByClass(clazz);
        cmdlet.setMethodName(method.getName());
        if(methods.equals(HttpMethods.GET)){
            GetMap.put(URI,cmdlet);
        }else if(methods.equals(HttpMethods.POST)){
            PostMap.put(URI,cmdlet);
        }
    }

    @Override
    public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        request.setCharacterEncoding("utf-8");
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("text/html:charset=UTF-8");

        String request_method= request.getMethod();
        AbstractRESTCmdlet cmdlet;
        UTF8Request utf8Request = new UTF8Request(request);

        if (request_method.equals(HttpMethods.GET)) {
            cmdlet = (AbstractRESTCmdlet) GetMap.getMatch(request.getUri().getPath()).getValue();
        } else if (request_method.equals(HttpMethods.POST)) {
            cmdlet = (AbstractRESTCmdlet) PostMap.getMatch(request.getUri().getPath()).getValue();
        } else {
            return;
        }

        String methodName = cmdlet.getMethodName();
        AbstractRESTResult result = null;
        logger.info("-->URI:{},Method :{}",new Object[]{utf8Request.getUri(),utf8Request.getMethod()});
        try {
            if(methodName == null){
                methodName = "execute";
            }
            result = (AbstractRESTResult)MethodUtils.invokeMethod(cmdlet,methodName,utf8Request);
            request.setHandled(true);
        } catch (Exception e) {
            JsonRESTResult jsonRESTResult = new JsonRESTResult();
            jsonRESTResult.setStatusCode(500);
            result = jsonRESTResult;
            logger.error("-->URI:{} 没找到对应的处理方法!", new Object[]{utf8Request.getUri()});
        }
        httpServletResponse.getWriter().write(result.toHttpResponse());
        long end=System.currentTimeMillis();

        long time =(end-start);
        logger.info("---> Execute time: {}us", time);
    }
}
