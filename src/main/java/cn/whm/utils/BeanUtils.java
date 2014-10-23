package cn.whm.utils;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by wanghm on 2014/10/23.
 */
public class BeanUtils {
    private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);
    //将同名属性赋值到新bean里面.
    public static void getNewBean(Object obj1,Object obj2){
        try{
            Map map = PropertyUtils.describe(obj2);//  BeanUtils.describe(obj2);
            Set keySet = map.keySet();
            for (Iterator iter = keySet.iterator(); iter.hasNext();) {
                Object element = (Object) iter.next();
                if(map.get(element) == null){
                    continue;
                }
                if(!canSetValue(map.get(element))){
                    continue;
                }
                try {
                    PropertyUtils.setProperty(obj1, String.valueOf(element), map.get(element));
                } catch (Exception e) {
                    continue;
                }
            }
        }catch (Exception e){
            logger.error("BeanUtils.getNewBean error:{}"+e);
        }
    }

    private static boolean canSetValue(Object param) {
        if (param instanceof Integer) {
            return true;
        } else if (param instanceof String) {
            return true;
        } else if (param instanceof Double) {
            return true;
        } else if (param instanceof Float) {
            return true;
        } else if (param instanceof Long) {
            return true;
        } else if (param instanceof Boolean) {
            return true;
        } else if (param instanceof Date) {
            return true;
        } else if (param instanceof BigDecimal) {
            return true;
        }
        return false;
    }
}
