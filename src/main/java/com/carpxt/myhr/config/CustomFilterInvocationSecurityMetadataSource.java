package com.carpxt.myhr.config;

import com.carpxt.myhr.model.Menu;
import com.carpxt.myhr.model.Role;
import com.carpxt.myhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @Author: tianjie
 * @Date: 2020/12/15 10:32
 * @Description:  权限设计：1、 该类主要是根据用户传来的请求地址，分析出请求需要的角色，比对用户是否具有此角色，
 * @Version: 1.0
 */

@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuService menuService;

    //比对工具
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    //返回当前访问需要的角色（Collection<ConfigAttribute>）
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //入参object实际是一个 FilterInvocation 对象   可以获取到请求的url地址
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        //得到所有的menus   这里每次请求进来都要去查询数据库  角色菜单是很少变化的 可以使用缓存
        List<Menu> menus = menuService.getAllMenusWithRole();
        for (Menu menu : menus) {
            //第一个参数是规则，第二个参数是需要比对的属性
            //antPathMatcher.match(pattern,path)
            if (antPathMatcher.match(menu.getUrl(),requestUrl)){
                //如果匹配上 就得到当前请求需要的角色
                List<Role> roleList = menu.getRoleList();
                // springSecurity需要我们返回 Collection<ConfigAttribute> 需要转换一下
                String[] str = new String[roleList.size()];
                for (int i = 0; i < roleList.size(); i++) {
                     str[i] = roleList.get(i).getName();
                }
                return SecurityConfig.createList(str);
            }
        }
        //没有匹配上的返回null,或者返回登录
        //ROLE_LOGIN  这里只是一个标记，在下一步判断 如果是这个就是没有匹配上的
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
