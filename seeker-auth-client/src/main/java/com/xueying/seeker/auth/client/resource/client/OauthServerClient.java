/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: Oauth客户端配置
 * Author:   Allen
 * Date:     2019/7/17
 * Description: Oauth客户端配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.client.resource.client;

import com.xueying.seeker.auth.client.resource.model.dto.TokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Oauth客户端配置
 *
 * @author Allen
 * @date 2019/7/17
 */
@FeignClient(value = "seeker-auth-server")
public interface OauthServerClient {
    /**
     * 默认授权方式
     */
    String GRANT_TYPE = "password";

    /**
     * 从marge cloud auth server获取token
     *
     * @param basicAuth clientId和clientSecret的base64加密
     * @param username 用户名
     * @param password 密码
     * @param grantType 授权方式默认使用password
     * @return TokenDTO 返回token数据
     */
    @RequestMapping(method = RequestMethod.POST, value = "/uaa/oauth/token",
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    TokenDTO token(@RequestHeader("Authorization") String basicAuth, @RequestParam("username") String username,
                   @RequestParam("password") String password, @RequestParam("grant_type") String grantType);
}