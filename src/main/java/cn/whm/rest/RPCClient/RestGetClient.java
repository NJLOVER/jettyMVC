package cn.whm.rest.RPCClient;

import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.Map;

/**
 * Created by wanghm on 2014/10/20.
 */
public class RestGetClient extends RestClientBase {
    private Logger logger = LoggerFactory.getLogger(RestGetClient.class);

    public String callRestRPC(String requestUri,Map<String,String> headerParam){
        try {
            HttpGet httpGet = new HttpGet();
            setUri(httpGet,requestUri);
            inithttpHeader(httpGet,headerParam);
            return getResponse(httpGet);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            logger.error("Error request uri: {}", requestUri);
        }
        return null;
    }
}
