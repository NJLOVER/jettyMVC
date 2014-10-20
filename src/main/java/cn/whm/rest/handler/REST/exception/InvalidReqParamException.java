package cn.whm.rest.handler.REST.exception;

/**
 * Created by wanghm on 2014/10/20.
 */
public class InvalidReqParamException extends Exception{
    public InvalidReqParamException()
    {
        super();
    }

    public InvalidReqParamException(String s) {
        super(s);
    }
}
