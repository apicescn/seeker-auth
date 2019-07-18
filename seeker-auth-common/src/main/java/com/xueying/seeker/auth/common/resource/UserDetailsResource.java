/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: UserDetailsResource
 * Author:   Allen
 * Date:     2019/7/17
 * Description: UserDetails用户信息接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.common.resource;

import com.xueying.seeker.auth.common.entity.UserInfo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * UserDetails用户信息接口
 * @author Allen
 * @date 2019/7/15
 */
public interface UserDetailsResource {
    /**
     * 根据用户名查询用户信息接口
     *
     *  @param username 用户名称
     *  @return 用户信息
     *  @throws UsernameNotFoundException 用户不存在异常信息
     */

    @RequestLine("POST /api/user/details")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    UserInfo loadUserByUsername(@Param("username") String username) throws UsernameNotFoundException;
}
