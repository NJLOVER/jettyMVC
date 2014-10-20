package cn.whm.rest.RPCClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by wanghm on 2014/10/20.
 */
public abstract class RestClientBase {
    private Logger logger = LoggerFactory.getLogger(RestClientBase.class);
    protected String host = null;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
    public void setUri(HttpRequestBase requestBase, String requestUri) throws URISyntaxException {
        String uri;
        if(this.getHost() != null){
            uri = host + requestUri;
        }else{
            uri = requestUri;
        }
        requestBase.setURI(new URI(uri));
    }

    public void inithttpHeader(HttpRequestBase request,Map<String,String> headerParam){
        if(headerParam != null){
            for(Map.Entry<String,String> entry : headerParam.entrySet()){
                request.addHeader(entry.getKey(),entry.getValue());
            }
        }
    }
    protected String getResponse(HttpRequestBase requestBase){
        try{
            DefaultHttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(requestBase);
            HttpEntity entity = response.getEntity();
            if(entity != null){
                return EntityUtils.toString(entity,"utf-8");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Http request failed on {}", requestBase.getURI().toString());
        }
        return null;
    }
}
