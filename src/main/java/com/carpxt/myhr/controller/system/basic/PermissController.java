package com.carpxt.myhr.controller.system.basic;

import com.carpxt.myhr.model.RespResult;
import com.carpxt.myhr.model.Role;
import com.carpxt.myhr.model.dto.MenuTreeDto;
import com.carpxt.myhr.model.from.UpdateMenusFrom;
import com.carpxt.myhr.service.MenuService;
import com.carpxt.myhr.service.PermissService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: tianjie
 * @Date: 2020/12/24 11:12
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Autowired
    PermissService permissService;



    @Autowired
    MenuService menuService;

    @GetMapping("/getPermissList")
    public List<Role> getPermissList(){
        return permissService.getPermissList();
    }

    @GetMapping("/getAllMenus")
    public List<MenuTreeDto> getAllMenus(){
        return menuService.getAllMenus();
    }

    @GetMapping("/getCheckMenus/{rid}")
    public List<Integer> getCheckMenus(@PathVariable("rid") Integer rid){
        return menuService.getCheckMenus(rid);
    }



    @PostMapping("/addPermiss")
    public RespResult addPermiss(@RequestBody Role role) {
        if (permissService.addPermiss(role) == 1) {
            return RespResult.ok("新增成功");
        }
        return RespResult.error("新增失败");

    }

    @PostMapping("/updateMenus")
    public RespResult updateMenus(@RequestBody UpdateMenusFrom from) {
        if (menuService.updateMenus(from) == from.getMids().size()) {
            return RespResult.ok("更新成功");
        }
        return RespResult.error("更新失败");
    }

    @GetMapping("/deleteRole/{rid}")
    public RespResult deleteRole(@PathVariable("rid") Integer rid) {
        if (permissService.deleteRole(rid) == 1) {
            return RespResult.ok("删除成功");
        }
        return RespResult.error("删除失败");

    }




}
