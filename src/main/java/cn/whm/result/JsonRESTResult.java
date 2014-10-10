package cn.whm.result;

import cn.whm.utils.JsonUtils;

import java.io.IOException;

/**
 * Created by wanghm on 2014/9/30.
 */
public class JsonRESTResult extends ObjectRESTResult {

    @Override
    public String convertOBJtoHttpResponse() {
        RestWrapper wrapper = new RestWrapper();
        wrapper.setReturnObj(getReturnObj());
        wrapper.setStatusCode(getStatusCode());
        wrapper.setMsg(getMsg());
        String jsonStr = "{}";
        try {
            jsonStr = JsonUtils.toJson(wrapper);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }
}
