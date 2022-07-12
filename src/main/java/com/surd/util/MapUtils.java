package com.surd.util;

import com.surd.one.Dir;
import com.surd.one.Group;
import com.surd.one.Tank;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author admin
 * @date
 */
public class MapUtils {
    private MapUtils(){}

    /**
     * 将object转为map集合
     * @param entity
     * @return map
     * @throws IllegalAccessException
     */
    public static Map<String, Object> beanToMap(Object entity) throws IllegalAccessException {
        if (null == entity){
            return Collections.emptyMap();
        }
        Map<String, Object> parameter = new HashMap<>();
        Field[]   fields   =   entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                Object o = field.get(entity);
                parameter.put(field.getName(), o);
            } catch (Exception e) {
                throw e;
            }
        }
        return parameter;
    }

    /**
     * 将map转换成url,如果值为空，则不进行处理
     * @param map parameter
     * @return  string, like this: a=1&b=2
     */
    public static String convertMapToUrl(Map<String, String> map) {
        if (null == map || map.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue();
            if (null != value && !"".equals(value) ){
                sb.append(entry.getKey() + "=" + entry.getValue());
                sb.append("&");
            }
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0,s.length()-1);
        }
        return s;
    }

    public static String getKey(Map map,String value,boolean islike){
        Set set=map.entrySet();
        Iterator it = set.iterator();
        while(it.hasNext()){
            Map.Entry entry=(Map.Entry)it.next();
            if(islike){
                if(entry.getValue().toString().contains(value)){
                    return entry.getKey().toString();
                }
            }else{
                if(entry.getValue().equals(value)) return entry.getKey().toString();
            }
        }
        return "";
    }
    public static void main(String[] args) throws IllegalAccessException {

        Map<String, String> urlMap = new HashMap<>();
        urlMap.put("a","d");
        urlMap.put("b","abcde");
        urlMap.put("aa","ccc");
        urlMap.put("a1a","ccc1");
        String urlParamsByMap = MapUtils.getKey(urlMap,"c",true);
        System.out.println(urlParamsByMap);
    }
}
