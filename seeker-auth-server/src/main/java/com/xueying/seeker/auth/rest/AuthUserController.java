/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: AuthUserController
 * Author:   Allen
 * Date:     2019/7/15
 * Description: AuthUserController接口服务
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.rest;

import io.swagger.annotations.ApiOperation;
import java.security.Principal;

import io.swagger.annotations.Api;

/**
 * 获取用户信息
 *
 * @author Allen
 * @date 2019-07-10
 */
@Api(tags = {"auth-user-controller"})
public interface AuthUserController {

    /**
     * 根据ID查询客户端url
     */
    String GET_USER = "/user";
    /**
     * 返回principal对象
     * 
     * @param user 用户信息
     * @return Principal
     */
    @ApiOperation(value = "用户信息查询", notes = "用户信息查询", protocols = "http,https", httpMethod = "GET")
    Principal user(Principal user);
}
