/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: UserDetailsServiceImpl
 * Author:   Allen
 * Date:     2019/7/15
 * Description: UserDetailsService的实现
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.service;

import com.xueying.seeker.auth.common.entity.UserInfo;
import com.xueying.seeker.auth.common.resource.UserDetailsResource;
import com.xueying.seeker.auth.config.feign.UserDetailsClientProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * UserDetailsService的实现
 *
 * @author Allen
 * @date 2019-07-10
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * 获取用户信息接口
     */
    @Autowired
    private Map<String, UserDetailsResource> userDetailsResources;

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException 用户不存在异常信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取client_id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String clientId;
        // 当授权模式为authorization_code,authentication为null,这时给设置一个默认的clientId用于对应一个获取用户信息服务
        if (authentication != null) {
            clientId = ((User)authentication.getPrincipal()).getUsername();
        } else {
            clientId = UserDetailsClientProperties.DEFAULT_CLIENT_ID;
        }
        // 获取用户
        UserDetailsResource resource = userDetailsResources.get(clientId);
        UserInfo userInfo = resource.loadUserByUsername(username);
        // 获取用户权限
        // List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // if (StringUtils.isNoneEmpty(user.getAuthorities())) {
        // Stream.of(user.getAuthorities().split(","))
        // .forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        // }
        if (userInfo == null) {
            throw new UsernameNotFoundException("Not found any user for username[" + username + "]");
        }
        return userInfo;

    }

}
