package cn.whm.utils;

import com.alibaba.fastjson.JSONArray;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by wanghm on 2014/10/8.
 */
public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static JsonFactory  jsonFactory = new JsonFactory();
    //初始化jackson使用配置信息
    static{
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS,false);
        objectMapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES,false);
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
    }

    public static <T> T fromJson(String jsonStr , Class<T> pojoClass) throws IOException {
        return objectMapper.readValue(jsonStr,pojoClass);
    }

    public static <T> T fromJson(FileReader fr , Class<T> pojoClass) throws IOException {
        return objectMapper.readValue(fr,pojoClass);
    }

    public static String toJson(Object pojo,boolean prettyPrint) throws IOException {
        StringWriter sw = new StringWriter();
        JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(sw);
        if(prettyPrint){
            jsonGenerator.useDefaultPrettyPrinter();
        }
        objectMapper.writeValue(jsonGenerator, pojo);
        return sw.toString();
    }

    public static String toJson(Object obj) throws IOException {
        return toJson(obj,false);
    }

    public static void toJson(Object obj ,FileWriter fw,boolean prettyPrint) throws IOException {
        JsonGenerator jg = jsonFactory.createJsonGenerator(fw);
        if(prettyPrint){
            jg.useDefaultPrettyPrinter();
        }
        objectMapper.writeValue(jg,obj);
    }

    public static Map<String,Object> parseMap(String jsonStr) throws IOException {
        Map<String,Object> objMap = objectMapper.readValue(jsonStr,Map.class);
        return objMap;
    }

    public ObjectMapper getObjectMapper(){
        return objectMapper;
    }

    public static <T> List<T> fromJsonToList(String jsonStr ,Class<T> pojo){
        return JSONArray.parseArray(jsonStr,pojo);
    }
}
