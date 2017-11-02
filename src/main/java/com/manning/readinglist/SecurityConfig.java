package com.manning.readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 使用Java形式进行显示配置，编写配置类
 * 配置用户访问路径和用户的操作逻辑
 * 通过继承webSecurityConfigurerAdapter
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ReaderRepositoty readerRepositoty;

    /**
     * 设置访问路径
     * "/"的请求（ReadingListController的方法映射到了该路径）只有经过身份认证且拥有Reader角色的用户才能访问
     * 其他所有请求想所有用户开放了访问权限
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/").access("hasRole('READER')") //要求登陆者有READER角色
                    .antMatchers("/**").permitAll()
                .and()
                //将登录页和登陆失败页指定到了/login
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error=true");
    }

    /**
     * 设置一个自定义的UserDatailService
     * 定义用户处理服务,可以是任意实现了UserDatailService的类。用于查找指定用户名的用户
     * 这里使用的是匿名内部类的方式实现的，简单的调用了注入的ReaderRepository的findOne()方法
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return readerRepositoty.findOne(username);
            }
        });
    }
}
