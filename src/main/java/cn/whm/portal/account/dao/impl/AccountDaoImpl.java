package cn.whm.portal.account.dao.impl;

import cn.whm.dao.mybatis.BaseMyBatisDao;
import cn.whm.portal.account.dao.AccountDao;
import cn.whm.portal.account.entity.Account;

/**
 * Created by wanghm on 2014/10/11.
 */
public class AccountDaoImpl extends BaseMyBatisDao<Account,String> implements AccountDao{

    @Override
    public String getMybatisNameSpace() {
        return "cn.whm.portal.account.entity.AccountMapper";
    }
}
