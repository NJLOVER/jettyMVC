package cn.whm.rest.RPCClient;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wanghm on 2014/10/20.
 */
public class RestPostClient extends RestClientBase {
    private Logger logger = LoggerFactory.getLogger(RestPostClient.class);

    public String callRestRPC(String requestURI,Map<String,String> headerMap,Map<String,String> bodyMap){
        try{
            HttpPost httpPost = new HttpPost();
            setUri(httpPost,requestURI);
            inithttpHeader(httpPost,headerMap);
            if(bodyMap!= null){
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                for(Map.Entry<String,String> entry : bodyMap.entrySet()){
                    params.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
                }
                HttpEntity entity = new UrlEncodedFormEntity(params,"utf-8");
                httpPost.setEntity(entity);
            }
            return getResponse(httpPost);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Error request param on : {}", requestURI);
        }
        return null;
    }
}
