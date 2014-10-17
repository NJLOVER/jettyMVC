package cn.whm.rest.handler;

import cn.whm.rest.handler.REST.RequestDateFormat;
import cn.whm.rest.handler.REST.RequestParamType;
import cn.whm.rest.handler.REST.RequestSource;

import java.lang.reflect.Type;

/**
 * Created by wanghm on 2014/10/17.
 */
public class ParamInfo {
    private Type parameterType;
    private String value;
    private RequestSource requestSource;
    private RequestParamType requestParamType;
    private RequestDateFormat requestDateFormat;
    private int minInt;
    private int maxInt;
    private boolean required;
    private boolean needValidate;
    private int maxStringLength;
    private int validateExp;
    private Object defaultValue;

    public Type getParameterType() {
        return parameterType;
    }

    public void setParameterType(Type parameterType) {
        this.parameterType = parameterType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public RequestSource getRequestSource() {
        return requestSource;
    }

    public void setRequestSource(RequestSource requestSource) {
        this.requestSource = requestSource;
    }

    public RequestParamType getRequestParamType() {
        return requestParamType;
    }

    public void setRequestParamType(RequestParamType requestParamType) {
        this.requestParamType = requestParamType;
    }

    public RequestDateFormat getRequestDateFormat() {
        return requestDateFormat;
    }

    public void setRequestDateFormat(RequestDateFormat requestDateFormat) {
        this.requestDateFormat = requestDateFormat;
    }

    public int getMinInt() {
        return minInt;
    }

    public void setMinInt(int minInt) {
        this.minInt = minInt;
    }

    public int getMaxInt() {
        return maxInt;
    }

    public void setMaxInt(int maxInt) {
        this.maxInt = maxInt;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isNeedValidate() {
        return needValidate;
    }

    public void setNeedValidate(boolean needValidate) {
        this.needValidate = needValidate;
    }

    public int getMaxStringLength() {
        return maxStringLength;
    }

    public void setMaxStringLength(int maxStringLength) {
        this.maxStringLength = maxStringLength;
    }

    public int getValidateExp() {
        return validateExp;
    }

    public void setValidateExp(int validateExp) {
        this.validateExp = validateExp;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }
}
