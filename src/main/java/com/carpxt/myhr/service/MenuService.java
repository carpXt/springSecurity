package com.carpxt.myhr.service;

import com.carpxt.myhr.mapper.MenuMapper;
import com.carpxt.myhr.model.Hr;
import com.carpxt.myhr.model.Menu;
import com.carpxt.myhr.model.dto.MenuTreeDto;
import com.carpxt.myhr.model.from.UpdateMenusFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: tianjie
 * @Date: 2020/12/14 16:12
 * @Description: TODO
 * @Version: 1.0
 */

@Service
@Transactional
public class MenuService {

    @Autowired
    MenuMapper menuMapper;



    public List<Menu> getMenusByHrId() {
        return menuMapper.getMenusByHrId(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

    //@Cacheable
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }

    public List<MenuTreeDto> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    public List<Integer> getCheckMenus(Integer rid) {
        return menuMapper.getCheckMenus(rid);
    }

    public int updateMenus(UpdateMenusFrom from) {
        menuMapper.deleteByRid(from.getRid());
        return menuMapper.updateMenus(from);
    }
}
