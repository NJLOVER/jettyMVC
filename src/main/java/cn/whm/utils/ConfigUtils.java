package cn.whm.utils;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wanghm on 2014/10/13.
 */
public class ConfigUtils {
    private static Logger logger = LoggerFactory.getLogger(ConfigUtils.class);

    private static Configuration combinedConfig = new CombinedConfiguration();

    private static Configuration propertisConfig = new PropertiesConfiguration();

    static{
        try{
            DefaultConfigurationBuilder defaultConfigurationBuilder = new DefaultConfigurationBuilder("app.config.xml");
            defaultConfigurationBuilder.setReloadingStrategy(new FileChangedReloadingStrategy());
            combinedConfig = defaultConfigurationBuilder.getConfiguration();
        }catch (Exception e){
            logger.error("Init configuration error",e);
        }
    }

    public static Iterator<String> getKeys(){
        return combinedConfig.getKeys();
    }
    public static boolean containKey(String key){
        return combinedConfig.containsKey(key);
    }
    public static Object getProperty(String key){
        return combinedConfig.getProperty(key);
    }
    public static String getString(String key){
        return combinedConfig.getString(key);
    }
    public static boolean getBoolean(String key) {
        return combinedConfig.getBoolean(key);
    }

    public static int getInt(final String key) {
        return combinedConfig.getInt(key);
    }
    public static int getIntValue(final String key) {
        return combinedConfig.getInt(key);
    }

    public static long getLong(final String key) {
        return combinedConfig.getLong(key);
    }

    public static long getLongValue(final String key) {
        return combinedConfig.getLong(key);
    }

    public static double getDouble(final String key) {
        return combinedConfig.getDouble(key);
    }

    public static double getDoubleValue(final String key) {
        return combinedConfig.getDouble(key);
    }

    public static BigDecimal getBigDecimal(String key) {
        return combinedConfig.getBigDecimal(key);
    }

    public static BigInteger getBigInteger(String key) {
        return combinedConfig.getBigInteger(key);
    }

    public static String getStringValue(String key) {
        return combinedConfig.getString(key);
    }

    public static String[] getStringArray(String key) {
        return combinedConfig.getStringArray(key);
    }

    public static List<Object> getList(String key) {
        return combinedConfig.getList(key);
    }
}
