package com.carpxt.myhr.config;

import com.carpxt.myhr.model.Hr;
import com.carpxt.myhr.model.RespResult;
import com.carpxt.myhr.service.HrService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: tianjie
 * @Date: 2020/12/12 14:54
 * @Description: springSecurity配置类
 * @Version: 1.0
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    HrService hrService;

    //权限设计 引入
    @Autowired
    CustomFilterInvocationSecurityMetadataSource metadataSource;

    @Autowired
    CustomURlDecisionManager decisionManager;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //所有的请求都要认证之后才能访问
//                .anyRequest().authenticated()
                //权限设计
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(decisionManager);
                        o.setSecurityMetadataSource(metadataSource);
                        return o;
                    }
                })
                .and()
                //表单登录
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                //处理登录的url
                .loginProcessingUrl("/doLogin")
                //登录页面 登录失败跳转的页面
                .loginPage("/login")
                //登录成功的跳转页面
                .successHandler(new AuthenticationSuccessHandler() {
                   // authentication  保存登录成功的用户信息
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp,
                                                        Authentication authentication) throws IOException, ServletException {

                        //登录成功返回json
                        resp.setContentType("application/json;charset=UTF-8");
                        PrintWriter out = resp.getWriter();
                        Hr hr = (Hr) authentication.getPrincipal();
                        //登录成功之后返回到前端 不要返回密码  还有另外一种方式是 在hr实体类上加注解 @jsonIgnore
                        hr.setPassword(null);
                        RespResult ok = RespResult.ok("登录成功!", hr);
                        String s = new ObjectMapper().writeValueAsString(ok);
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                //登录失败的回调
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        RespResult respResult = RespResult.error("登录失败");
                        //登录失败有很多中原因  需要判断
                        //快捷键  ctrl+H -> ctrl+H 就知道所有的Exception
                        if (exception instanceof LockedException){
                            respResult.setMsg("账户被锁定，清联系管理员");
                        } else if (exception instanceof CredentialsExpiredException){
                            respResult.setMsg("密码过期，清联系管理员");
                        }else if (exception instanceof AccountExpiredException){
                            respResult.setMsg("账户过期，清联系管理员");
                        }else if (exception instanceof DisabledException){
                            respResult.setMsg("账户被禁用，清联系管理员");
                        }else if (exception instanceof BadCredentialsException) {
                            respResult.setMsg("用户名或密码输入错误,请重新输入");
                        }
                        String s = new ObjectMapper().writeValueAsString(respResult);
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                //默认的接口地址  可以自定义 .logoutUrl("/**")
                .logout()
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write(new ObjectMapper().writeValueAsString( RespResult.ok("注销成功！")));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                //暂时先关闭
                .csrf().disable();

    }
}
