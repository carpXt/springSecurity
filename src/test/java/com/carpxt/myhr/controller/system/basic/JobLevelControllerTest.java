package com.carpxt.myhr.controller.system.basic;


import com.carpxt.myhr.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JobLevelControllerTest {


    @Autowired
    JobLevelController controller;

    @Test
    public void getJobLevelList(){
        JsonUtils.toJsonP(controller.getJobLevelList()) ;
    }

}