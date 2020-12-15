package com.carpxt.myhr.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: tianjie
 * @Date: 2020/12/12 15:23
 * @Description: 返回bean
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
public class RespResult<T> {

    private Integer status;
    private String msg;
    private Object obj;

    public static RespResult ok(String msg){
        return new RespResult(0,msg,null);
    }
    public static RespResult ok(String msg,Object obj){
        return new RespResult(0,msg,obj);
    }

    public static RespResult error(String msg){
        return new RespResult(-1,msg,null);
    }
    public static RespResult error(String msg,Object obj){
        return new RespResult(-1,msg,obj);
    }

}
