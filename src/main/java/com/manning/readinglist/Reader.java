package com.manning.readinglist;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

/**
 * jpa实体类
 * id:username
 * 实现了UserDetails接口及其中的方法，这样Reader就能代表Spring Security里的用户了
 * lala
 */
@Entity
public class Reader implements UserDetails{

    private static final long serialVersionUID = 1L;

    //用户名
    @Id
    private String username;

    //用户全名
    private String fullname;
    //用户密码
    private String password;

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 授予用户权限的方法
     * 现在设置为授予READER权限
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("READER"));
    }

    /**
     * 登陆账号是否有时间限制
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 登陆账号是否加锁
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 登陆账号是否有认证时间
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否禁用登陆账号
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
