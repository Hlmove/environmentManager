package com.briup.common.utils;

import jdk.nashorn.internal.runtime.regexp.JoniRegExp;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @Auther: vanse(lc)
 * @Date: 2023/9/21-09-21-17:02
 * @Description：对象拷贝功能
 */
public class CopyUtil {
    // Environment  e1 -> e2
    // 忽略属性名字 name data
    // s  -> ss
    public static <T> T copy(T origin,String...ingorePropertity) throws Exception {
        // 1.获取Class文件
        Class<?> aClass = origin.getClass();
        // 2.根据原对象创建新对象
        Object ss = aClass.newInstance();
        // 3.设置新对象的属性（来自原对象）
        Field[] fields = aClass.getDeclaredFields();
        // 类上所有的属性名称 id name age
        for (Field field : fields) {
//            System.out.println(field.getModifiers());
//            System.out.println("Modifier.toString(field.getModifiers()) = " + Modifier.toString(field.getModifiers()));
            String m = Modifier.toString(field.getModifiers()); // private static final
            if(m.contains("static final")){
                continue;
            }
            // private | static |final
            // static final
            // 忽略属性不复制 name       age name
                // id  equals  ingorePropertity[] 不复制
            boolean flag = true; // 不需要忽略
            for (String ignore : ingorePropertity) {
                if(field.getName().equals(ignore)){
                    flag = false; // 需要忽略
                }
            }
            if(flag){ // 不需要忽略 复制
                field.setAccessible(true);
                Object oldValue = field.get(origin); // id 1
                field.set(ss,oldValue);
            }
        }


        return (T) ss;
    }

}
