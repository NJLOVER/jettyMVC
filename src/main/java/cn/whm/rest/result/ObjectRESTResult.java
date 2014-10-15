package cn.whm.rest.result;

/**
 * Created by wanghm on 2014/9/30.
 */
public abstract class ObjectRESTResult extends AbstractRESTResult {
    private int statusCode;
    private String msg;
    private Object returnObj;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getReturnObj() {
        return returnObj;
    }

    public void setReturnObj(Object returnObj) {
        this.returnObj = returnObj;
    }

    public abstract String convertOBJtoHttpResponse();
    @Override
    public String toHttpResponse() {
        return convertOBJtoHttpResponse();
    }
}
