package cn.whm.handler;

import org.eclipse.jetty.http.PathMap;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wanghm on 2014/9/28.
 */
public class DispathRestFullHandler extends AbstractHandler{
    private PathMap PostMap;
    private PathMap GetMap;

    public DispathRestFullHandler(){
        PostMap = new PathMap(100);
        GetMap = new PathMap(100);

        PostMap.put("/*",new DebugRESTCmdlet());
        GetMap.put("/*",new DebugRESTCmdlet());

    }

    private dispath

    @Override
    public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {

    }
}
