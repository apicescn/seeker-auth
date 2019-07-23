/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: GrantType
 * Author:   Allen
 * Date:     2019/7/15
 * Description: oauth2.0的4种模式
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.config.security;

/**
 * oauth2.0的4种模式
 *
 * @author Allen
 * @date 2019-07-10
 */
public class GrantType {
    /**
     * 认证码模式
     */
    public static final String AUTHORIZATION_CODE = "authorization_code";
    /**
     * 隐式授权模式
     */
    public static final String IMPLICIT = "implicit";
    /**
     * 客户端认证模式
     */
    public static final String CLIENT_CREDENTIALS = "client_credentials";
    /**
     * password模式
     */
    public static final String PASSWORD = "password";
    /**
     * 刷新token
     */
    public static final String REFRESH_TOKEN = "refresh_token";
}
