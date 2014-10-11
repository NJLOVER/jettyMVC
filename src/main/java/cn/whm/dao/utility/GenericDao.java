package cn.whm.dao.utility;

import org.springframework.dao.DataAccessException;

import javax.xml.crypto.Data;
import java.io.Serializable;

/**
 * Created by wanghm on 2014/10/9.
 */
public interface GenericDao<E, PK extends Serializable> {
    E getByid(PK id) throws DataAccessException;

    int deleteById(PK id) throws DataAccessException;

    int save(E entity) throws DataAccessException;

    int update(E entity) throws DataAccessException;

    /** 用于hibernate.flush() 有些dao实现不需要实现此类  */
    void flush() throws DataAccessException;
}
