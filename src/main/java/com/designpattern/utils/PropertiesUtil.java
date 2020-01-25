package com.designpattern.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
//    public static void main(String[] args) {
//        System.out.println(PropertiesUtil.class.getResource("").getPath());
//        System.out.println(PropertiesUtil.class.getResource("/").getPath());
//        System.out.println(PropertiesUtil.class.getClassLoader().getResource("").getPath());
//    }
    public static String getValue(String key) throws IOException {
        Properties p = new Properties();
        p.load(PropertiesUtil.class.getResourceAsStream("/config.properties"));
        return p.getProperty(key);
    }
}
