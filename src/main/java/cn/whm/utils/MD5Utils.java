package cn.whm.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.security.MessageDigest;

/**
 * Created by wanghm on 2014/10/23.
 */
public class MD5Utils {
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public static String getMD5Code(String str){
        if(str == null || str.equals("")){
            return str;
        }
        //加密MD5
        String md5Str = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] results = md.digest(str.getBytes());
            String resultString = byteArrayToHexString(results);
            md5Str = resultString.toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5Str;
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
//    @Test
//    public void test(){
//        System.out.println(getMD5Code("123321"));
//    }
}
