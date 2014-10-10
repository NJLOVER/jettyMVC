package cn.whm.dao.mybatis;

import cn.whm.dao.utility.Page;
import org.apache.commons.beanutils.PropertyUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.util.ReflectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by wanghm on 2014/10/9.
 */
public class MybatisDao extends SqlSessionDaoSupport{
    protected <T> Page<T> selectPage(Page<T> page,String statementName,Object paramObj){
        String countStateName = statementName+"Count";
        return selectPage(page,statementName,countStateName,paramObj);
    }

    private <T> Page<T> selectPage(Page<T> page, String statementName, String countStatementName, Object parameter){
        Number totalCount = getSqlSession().selectOne(countStatementName, parameter);
        if(totalCount !=null && totalCount.longValue()>0 ){
            List list = getSqlSession().selectList(statementName,toMapParam(parameter,page));
            page.setResult(list);
            page.setTotalCount(totalCount.longValue());
        }
        return page;
    }

    private Map toMapParam(Object paramObj,Page p){
        Map map = toParameterMap(paramObj);
        map.put("offset", p.getOffset());
        map.put("limit", p.getPageSize());
        return map;
    }
    private static Map toParameterMap(Object parameter) {
        if (parameter instanceof Map) {
            return (Map) parameter;
        } else {
            try {
                return PropertyUtils.describe(parameter);
            } catch (Exception e) {
                ReflectionUtils.handleReflectionException(e);
                return null;
            }
        }
    }
}
