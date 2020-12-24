package com.carpxt.myhr.service;

import com.carpxt.myhr.mapper.RoleMapper;
import com.carpxt.myhr.model.Position;
import com.carpxt.myhr.model.Role;
import com.carpxt.myhr.model.dto.MenuTreeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: tianjie
 * @Date: 2020/12/24 11:13
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@Transactional
public class PermissService {

    @Autowired
    RoleMapper roleMapper;

    public List<Role> getPermissList() {
        return roleMapper.getPermissList();
    }

    public int addPermiss(Role role) {
        return roleMapper.insertSelective(role);
    }


    public int deleteRole(Integer rid) {
        return roleMapper.deleteByPrimaryKey(rid);
    }
}
