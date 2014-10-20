package cn.whm.rest.handler.REST.exception;

import java.lang.annotation.Annotation;

/**
 * Created by wanghm on 2014/10/20.
 */
public class AnnotateTypeErrorException extends Exception {
    private Annotation errorAnn;

    public AnnotateTypeErrorException(Annotation errorAnn){
        this.errorAnn = errorAnn;
    }

    public Annotation getErrorAnn() {
        return errorAnn;
    }
}
