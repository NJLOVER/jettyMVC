package cn.whm.rest.handler.REST;

import cn.whm.rest.annotate.*;
import cn.whm.rest.handler.ParamInfo;
import cn.whm.rest.handler.REST.exception.AnnotateTypeErrorException;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wanghm on 2014/10/20.
 */
public class ParamInfoUtils {
    public static ParamInfo parseParamInfo(Annotation paramAnnotation) throws AnnotateTypeErrorException {
        ParamInfo pi = null;
        if(paramAnnotation instanceof IntParam){
            pi = parseIntParamInfo(paramAnnotation);
        } else if (paramAnnotation instanceof StringParam)
        {
            pi = parseStringParamInfo(paramAnnotation);
        } else if (paramAnnotation instanceof TypedParam)
        {
            pi = parseTypedParamInfo(paramAnnotation);
        } else if (paramAnnotation instanceof DateParam)
        {
            pi = parseDateParamInfo(paramAnnotation);
        } else if (paramAnnotation instanceof BigDecimalParam)
        {
            pi = parseBigDecimalParamInfo(paramAnnotation);
        }
        if (pi == null)
        {
            throw new AnnotateTypeErrorException(paramAnnotation);
        }
        return pi;
    }

    private static ParamInfo parseBigDecimalParamInfo(Annotation annotation)
    {
        ParamInfo pi = new ParamInfo();
        BigDecimalParam dateParam = (BigDecimalParam)annotation;

        pi.setParameterType(BigDecimal.class);
        pi.setValue(dateParam.value());
        pi.setRequestParamType(dateParam.paramType());
        pi.setRequestSource(dateParam.source());
        pi.setDefaultValue(null);
        if (dateParam.required())
        {
            pi.setRequired(true);
        }
        return pi;
    }

    private static ParamInfo parseDateParamInfo(Annotation annotation)
    {
        ParamInfo pi = new ParamInfo();
        DateParam dateParam = (DateParam)annotation;

        pi.setParameterType(Date.class);
        pi.setValue(dateParam.value());
        pi.setRequestParamType(dateParam.paramType());
        pi.setRequestSource(dateParam.source());
        pi.setRequestDateFormat(dateParam.format());
        pi.setDefaultValue(null);
        if (dateParam.required())
        {
            pi.setRequired(true);
        }
        return pi;
    }

    private static ParamInfo parseIntParamInfo(Annotation paramAnnotation) {
        ParamInfo pi = new ParamInfo();
        IntParam intParam = (IntParam)paramAnnotation;
        pi.setParameterType(Integer.class);
        pi.setValue(intParam.value());
        pi.setRequestParamType(intParam.paramType());
        pi.setRequestSource(intParam.source());
        pi.setDefaultValue(intParam.defaultValue());
        if (intParam.required())
        {
            pi.setRequired(true);
            pi.setMaxInt(intParam.max());
            pi.setMinInt(intParam.min());
            pi.setNeedValidate(intParam.validated());
        }
        return pi;
    }

    private static ParamInfo parseStringParamInfo(Annotation annotation)
    {
        ParamInfo pi =new ParamInfo();
        StringParam stringParam = (StringParam)annotation;
        pi.setParameterType(String.class);
        pi.setValue(stringParam.value());
        pi.setRequestSource(stringParam.source());
        pi.setRequestParamType(stringParam.paramType());
        pi.setDefaultValue(stringParam.defaultValue());
        if (stringParam.required())
        {
            pi.setRequired(true);
            pi.setMaxStringLength(stringParam.maxLength());
            pi.setNeedValidate(stringParam.validated());
        }
        return pi;
    }

    private static ParamInfo parseTypedParamInfo(Annotation annotation)
    {
        ParamInfo pi = new ParamInfo();
        TypedParam typedParam = (TypedParam)annotation;
        pi.setParameterType(typedParam.type());
        pi.setValue(typedParam.value());
        pi.setRequestSource(typedParam.source());
        pi.setRequestParamType(typedParam.paramType());
        pi.setDefaultValue(null);
        if (typedParam.required())
        {
            pi.setRequired(typedParam.required());
            pi.setNeedValidate(typedParam.validated());
        }

        return pi;
    }
}
