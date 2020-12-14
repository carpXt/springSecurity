package com.carpxt.myhr.controller;

import com.carpxt.myhr.model.RespResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tianjie
 * @Date: 2020/12/12 16:10
 * @Description: TODO
 * @Version: 1.0
 */

@RestController
public class LoginController {

    @GetMapping("/login")
    public RespResult login(){
        return RespResult.error("尚未登录，请先登录！");
    }
}
