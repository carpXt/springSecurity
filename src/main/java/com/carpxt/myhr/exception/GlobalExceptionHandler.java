package com.carpxt.myhr.exception;

import com.carpxt.myhr.model.RespResult;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;


/**
 * @Author: tianjie
 * @Date: 2020/12/16 17:06
 * @Description: TODO
 * @Version: 1.0
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLException.class)
    public RespResult sqlException(SQLException e){
        return RespResult.error("数据库异常，操作失败！");
    }

    @ExceptionHandler(MySQLIntegrityConstraintViolationException.class)
    public RespResult sqlException(){
        return RespResult.error("该数据存在关联数据，操作失败！");
    }

}
