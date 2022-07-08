package com.surd.one;

import com.sun.deploy.util.StringUtils;

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

    public static int getInt(String key){
        String string = getString(key);
        if (null == string){
            return 0;
        }
        return Integer.valueOf(string);
    }

    public static String getString(String key){
        Object o = get(key);
        if (null == o){
            return null;
        }
        return String.valueOf(o);
    }

    public static void main(String[] args) {
        System.out.println(PropertyMgr.get("initTankCount"));
    }
}
