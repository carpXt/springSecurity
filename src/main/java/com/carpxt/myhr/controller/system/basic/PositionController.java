package com.carpxt.myhr.controller.system.basic;

import com.carpxt.myhr.model.Position;
import com.carpxt.myhr.model.RespResult;
import com.carpxt.myhr.model.from.DeletePosFrom;
import com.carpxt.myhr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: tianjie
 * @Date: 2020/12/16 13:35
 * @Description: 基础信息设置
 * @Version: 1.0
 */

@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {


    @Autowired
    PositionService positionService;

    /**
     * <p>查询所有的职位</p>
     * @param: []
     * @date: 2020/12/16 13:57
     * @return: java.util.List<com.carpxt.myhr.model.Position>
     */
    @GetMapping("/getPositionList")
    public List<Position> getPositionList(){
        return positionService.getPositionList();
    }

    @PostMapping("/addPosition")
    public RespResult addPosition(@RequestBody Position position){
        Integer integer = positionService.addPosition(position);
        if (integer==1){
            return RespResult.ok("添加成功");
        }
        return RespResult.error("添加失败");
    }

    @PostMapping("/updatePos")
    public RespResult updatePos(@RequestBody Position position){
        Integer integer = positionService.updatePos(position);
        if (integer==1){
            return RespResult.ok("更新成功");
        }
        return RespResult.error("更新失败");
    }


    @GetMapping("/deletePos/{id}")
    public RespResult deletePos(@PathVariable("id") Integer id){
        Integer integer = positionService.deletePos(id);
        if (integer==1){
            return RespResult.ok("删除成功");
        }
        return RespResult.error("删除失败");
    }

    /**
     * <p>批量删除</p>
     * @param: [id]
     * @date: 2020/12/16 19:02
     * @return: com.carpxt.myhr.model.RespResult
     */
    @PostMapping("/deletePosByIds")
    public RespResult deletePosByIds(@RequestBody DeletePosFrom deletePosFrom){
        if (positionService.deletePosByIds(deletePosFrom)==deletePosFrom.getIds().size()){
            return RespResult.ok("删除成功");
        }
        return RespResult.error("删除失败");
    }

}
