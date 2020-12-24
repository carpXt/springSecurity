package com.carpxt.myhr.controller.system.basic;


import com.carpxt.myhr.JsonUtils;
import com.carpxt.myhr.model.Position;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @Author: tianjie
 * @Date: 2020/12/14 16:12
 * @Description: position测试类
 * @Version: 1.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PositionControllerTest {

    @Autowired
    PositionController positionController;


    /**
     * <p>查询所有的 position</p>
     * @param: []
     * @date: 2020/12/16 13:57
     * @return: void
     */
    @Test
    public void getPositionList(){
       JsonUtils.toJsonP(positionController.getPositionList()) ;
    }


    @Test
    public void addPosition(){
        Position position = new Position();
        position.setName("测试职位1");
        JsonUtils.toJsonP(positionController.addPosition(position)) ;
    }

    @Test
    public void updatePos(){
        Position position = new Position();
        position.setName("测试职位w");
        position.setId(37);
        JsonUtils.toJsonP(positionController.updatePos(position)) ;
    }

    @Test
    public void deletePos(){

        JsonUtils.toJsonP(positionController.deletePos(37)) ;
    }

}