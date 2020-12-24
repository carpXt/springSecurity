package com.carpxt.myhr;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * created by zhuwenquan on 2018/9/5
 */
public class JsonUtils {
    /**
     * 格式化输出json到控制台
     * @param object
     */
    public static String toJsonP(Object object){
        if ( object != null ) {
            try {
                String pat1 = "yyyy-MM-dd HH:mm:ss" ;
                SimpleDateFormat sdf1 = new SimpleDateFormat(pat1) ;
                String ret = new ObjectMapper().writerWithDefaultPrettyPrinter().withDateFormat(sdf1).writeValueAsString(object) ;
                System.out.println(object.getClass().getName());
                System.out.println(ret);
                return ret;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            object = "";
        }
        return object.toString();
    }
}
