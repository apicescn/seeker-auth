/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: UserInfo
 * Author:   Allen
 * Date:     2019/7/17
 * Description: 用户信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.common.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户信息
 *
 * @author Allen
 * @date 2019/7/17
 */
public class UserInfo implements UserDetails {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6445503202868858500L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 是否可用
     */
    private Boolean enabled;
    /**
     * 权限
     */
    private List<SimpleGrantedAuthority> authorities;

    public UserInfo() {
    }

    /**
     * 构造函数
     * @param username 用户名
     * @param password 密码
     * @param enabled 是否可用
     * @param authorities 权限
     */
    public UserInfo(String username, String password, Boolean enabled,
        List<SimpleGrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    /**
     * 权限
     *
     * @return 返回权限集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * 密码
     *
     * @return 密码
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * 用户名
     *
     * @return 用户名
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * 账号是否不过期
     *
     * @return 默认返回true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否不锁定
     *
     * @return 返回enabled
     */
    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    /**
     * 凭证是否不过期
     *
     * @return 默认返回true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账号是否可用
     *
     * @return 返回enabled
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}