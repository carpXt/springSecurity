package com.carpxt.myhr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tianjie
 * @Date: 2020/12/12 15:00
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }


    @GetMapping("/employee/basic/hello")
    public String hello1(){
        return "/employee/basic/hello";
    }

    @GetMapping("/employee/advanced/hello")
    public String hello2(){
        return "/employee/advanced/hello";
    }

    @GetMapping("/personnel/train/hello")
    public String hello3(){
        return "/personnel/train/hello";
    }


    @GetMapping("/personnel/salary/hello")
    public String hello4(){
        return "/personnel/salary/hello";
    }

}
