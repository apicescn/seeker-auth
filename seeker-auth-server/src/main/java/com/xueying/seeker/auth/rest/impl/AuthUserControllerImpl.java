/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: AuthUserControllerImpl
 * Author:   Allen
 * Date:     2019/7/15
 * Description: 获取用户信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.rest.impl;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.xueying.seeker.auth.rest.AuthUserController;

/**
 * 获取用户信息
 *
 * @author Allen
 * @date 2019-07-10
 */
@RestController
@SessionAttributes("authorizationRequest")
public class AuthUserControllerImpl implements AuthUserController {

    /**
     * 返回principal对象
     * 
     * @param user 用户信息
     * @return Principal
     */
    @Override
    @RequestMapping(value = GET_USER)
    public Principal user(Principal user) {
        System.out.println(user);
        return user;
    }
}
