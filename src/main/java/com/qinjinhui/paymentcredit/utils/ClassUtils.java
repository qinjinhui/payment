package com.qinjinhui.paymentcredit.utils;

/**
 * @Author qinjinhui
 * @Date 2022/9/23
 * @Describe
 **/
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClassUtils {
    // getClassName("top.lingkang.demohibernate.entity")
    public static Class[] getClassByPackage(String packageName) {
        List<String> classListAll= new ArrayList<>();
        try {
            Enumeration<URL> resources = ClassUtils.class.getClassLoader().getResources(packageName.replaceAll("\\.", "/"));
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                String[] file = new File(url.getFile()).list();
                Class[] classList = new Class[file.length];
                for (int i = 0; i < file.length; i++) {
                    classList[i] = Class.forName(packageName + "." + file[i].replaceAll("\\.class", ""));
                    //classListAll.add(file[i]);
                }
                return classList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

