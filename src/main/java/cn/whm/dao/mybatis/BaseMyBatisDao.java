package cn.whm.dao.mybatis;

import cn.whm.dao.utility.GenericDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Created by wanghm on 2014/10/9.
 */
public abstract class BaseMyBatisDao<E,PK extends Serializable> extends MybatisDao implements GenericDao<E,PK> {
    private static Logger logger = LoggerFactory.getLogger(BaseMyBatisDao.class);

    public E getByid(PK id){
        return (E)getSqlSession().selectOne(getMybatisNameSpace()+".getById",id);
    }
    public int deleteById(PK id){
        int affectCount = getSqlSession().delete(getMybatisNameSpace()+"delById",id);
        return affectCount;
    }
    public int save(E entity){
        int affectCount = getSqlSession().insert(getMybatisNameSpace()+".insert",entity);
        return affectCount;
    }
    public int update(E entity){
        int affectCount = getSqlSession().update(getMybatisNameSpace()+".update",entity);
        return affectCount;
    }

    public abstract String getMybatisNameSpace();

    /**
     * 用于子类覆盖,在insert,update之前调用
     * @param o
     */
    protected void prepareObjectForSaveOrUpdate(E o) {
    }

    public void flush() {
        // ignore
    }
}
