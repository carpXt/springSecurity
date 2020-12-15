package com.carpxt.myhr.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author: tianjie
 * @Date: 2020/12/15 14:26
 * @Description: 权限设计： 2、判断用户是否具备 步骤 1 返回的 角色
 * @Version: 1.0
 */

@Component
public class CustomURlDecisionManager implements AccessDecisionManager {


    /**
     * <p></p>
     * @param: [authentication : 用户的信息，就是登录时返回的
     *          object : 请求对象
     *          collection : 需要的角色信息，就是步骤 1 比对返回的  ]
     * @date: 2020/12/15 14:30
     * @return: void
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : collection) {
            //得到需要的角色
            String needRole = configAttribute.getAttribute();
            if ("ROLE_LOGIN".equals(needRole)){
                //说明需要登录之后才能访问
                //判断有没有登录
                if (authentication instanceof AnonymousAuthenticationToken){
                    throw new AccessDeniedException("尚未登录，请登录！");
                }else {
                    //如果登录了就直接返回
                    return;
                }
            }
            //得到用户的角色信息
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                //判断用户的角色信息是否和需要的角色信息匹配  如果满足其中一个角色就可以访问
                if (authority.getAuthority().equals(needRole)){
                    return;
                }
            }
        }
        //如果以上条件都不满足
        throw new AccessDeniedException("权限不足，请联系管理员");

    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
