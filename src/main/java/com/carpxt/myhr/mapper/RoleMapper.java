package com.carpxt.myhr.mapper;

import com.carpxt.myhr.model.Position;
import com.carpxt.myhr.model.Role;
import com.carpxt.myhr.model.dto.MenuTreeDto;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getPermissList();


    List<MenuTreeDto> getAllMenus();
}