package cn.whm.handler;

import cn.whm.result.AbstractRESTResult;
import cn.whm.result.PlainTextRESTResult;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Writer;
import java.util.Enumeration;

/**
 * Created by wanghm on 2014/9/30.
 */
public class DebugRESTCmdlet extends AbstractRESTCmdlet{
    Logger logger = LoggerFactory.getLogger(DebugRESTCmdlet.class);

    public AbstractRESTResult execute(Request request){
        PlainTextRESTResult plainTextRESTResult = new PlainTextRESTResult();
        Writer  req_writer = plainTextRESTResult.getWriter();
        try{
            String host = request.getRemoteHost();
            String meth = request.getMethod();
            req_writer.write(String.format("request url is %s \n",host));
            req_writer.write(String.format("request method is %s \n",meth));

            Enumeration headerNames = request.getHeaderNames();
            if(headerNames.hasMoreElements()){
                String headerName = headerNames.nextElement().toString();
                req_writer.write(String.format("%s :%s /n",headerName,request.getHeader(headerName)));
            }
            req_writer.write("Paramter :\n");
            Enumeration paramEnum = request.getParameterNames();
            if(paramEnum.hasMoreElements()){
                String paramName = paramEnum.nextElement().toString();
                req_writer.write(String.format("%s :%s \n",paramName,request.getParameter(paramName)));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return plainTextRESTResult;
    }
}
