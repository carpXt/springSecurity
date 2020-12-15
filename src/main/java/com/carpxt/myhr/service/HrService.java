package com.carpxt.myhr.service;

import com.carpxt.myhr.mapper.HrMapper;
import com.carpxt.myhr.model.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: tianjie
 * @Date: 2020/12/12 14:47
 * @Description: 实现 UserDetailsService接口 重写方法 loadUserByUsername；
 * @Version: 1.0
 */


@Service
@Transactional
public class HrService implements UserDetailsService {

    @Autowired
    HrMapper hrMapper;

    /**
     * <p>通过用户名查找用户</p>
     * @param: [username]
     * @date: 2020/12/12 14:50
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Hr hr = hrMapper.loadUserByUsername(username);
      if (hr==null){
          throw new UsernameNotFoundException("用户名不存在");
      }

      hr.setRoleList(hrMapper.getRolesByid(hr.getId()));
        return hr;
    }
}
