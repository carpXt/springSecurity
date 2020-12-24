package com.carpxt.myhr;

import com.carpxt.myhr.controller.system.basic.PermissController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyhrApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    PermissController controller;

    @Test
    public void test001(){
        JsonUtils.toJsonP(controller.getAllMenus());
    }

    @Test
    public void test002(){
        JsonUtils.toJsonP(controller.getCheckMenus(2));
    }

}
