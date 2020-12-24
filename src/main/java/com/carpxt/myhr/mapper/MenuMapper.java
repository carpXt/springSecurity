package com.carpxt.myhr.mapper;

import com.carpxt.myhr.model.Menu;
import com.carpxt.myhr.model.dto.MenuTreeDto;
import com.carpxt.myhr.model.from.UpdateMenusFrom;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> getMenusByHrId(Integer hrId);

    List<Menu> getAllMenusWithRole();

    List<MenuTreeDto> getAllMenus();

    List<Integer> getCheckMenus(Integer rid);

    int updateMenus(UpdateMenusFrom from);

    void deleteByRid(Integer rid);
}