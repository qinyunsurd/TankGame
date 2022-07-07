package com.surd.one;

import java.io.IOException;
import java.util.Properties;

/**
 * @author admin
 * @date 配置文件管理
 */
public class PropertyMgr {
    static Properties pros = null;
    static {
        try {
            pros = new Properties();
            pros.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key){
        if (null == pros){
            return null;
        }
        return pros.get(key);
    }

    public static void main(String[] args) {
        System.out.println(PropertyMgr.get("initTankCount"));
    }
}
