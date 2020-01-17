/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: PlusOAuth2FeignRequestInterceptor
 * Author:   Allen
 * Date:    2019/7/17
 * Description: Oauth2的请求配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.feign.interceptor;

import feign.RequestTemplate;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * Oauth2的请求配置
 *
 * @author Allen
 * @create 2019/7/17
 */
public class PlusOAuth2FeignRequestInterceptor extends OAuth2FeignRequestInterceptor {
    /**
     * 请求头
     */
    private static final String HEADER_AUTHORIZATION = "Authorization";

    /**
     * 构造函数
     * 
     * @param oAuth2ClientContext
     * @param resource
     */
    public PlusOAuth2FeignRequestInterceptor(OAuth2ClientContext oAuth2ClientContext,
                                             OAuth2ProtectedResourceDetails resource) {
        super(oAuth2ClientContext, resource);
    }

    /**
     * 如果feign请求已经有请求头Authorization，就不进行添加
     * 
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        if (!template.headers().containsKey(HEADER_AUTHORIZATION)) {
            super.apply(template);
        }
    }
}
