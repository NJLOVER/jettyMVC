package cn.whm.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by wanghm on 2014/10/23.
 */
public class PasswdUtils {
    public static final int DEFAULT_PREFIX = 32;
    public static int prefix;
    static{
        prefix = DEFAULT_PREFIX;
    }
    //加密
    public static String generator(String pwd){
        StringBuffer sf = new StringBuffer();
        //MD5摘要密码
        String md5 = MD5Utils.getMD5Code(pwd);
        //生成随机前缀
        String random = RandomStringUtils.randomAlphanumeric(prefix);
        //生成sha384散列
        String sha = DigestUtils.sha384Hex(random+md5);
        //前缀+sha384散列作为密码
        sf.append(random).append(sha);
        return sf.toString();
    }

    public static boolean checkPwd(String inPwd,String secret){
        //将输入密码加密处理
        String md5 = MD5Utils.getMD5Code(inPwd);
        //获取随机前缀
        String random = secret.substring(0,prefix);
        //生成sha384散列
        String sha = DigestUtils.sha384Hex(random+md5);

        return secret.equals(random+sha);
    }
}
