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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException 用户不存在异常信息
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String finalPassword = bCryptPasswordEncoder.encode("123456");
        UserInfo userInfo = new UserInfo("superadmin", finalPassword, true, new ArrayList());
        return userInfo;

    }

}
