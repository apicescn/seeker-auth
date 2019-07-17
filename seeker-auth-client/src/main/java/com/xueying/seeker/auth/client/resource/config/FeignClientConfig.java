/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: FeignConfig
 * Author:   Allen
 * Date:     2019/5/18 9:59
 * Description: feign配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.client.resource.config;

import com.xueying.seeker.auth.client.resource.interceptor.PlusOAuth2FeignRequestInterceptor;
import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;


/**
 * feign配置
 *
 * @author Allen
 * @create 2019/7/17
 */
@Configuration
@EnableFeignClients(basePackages = "com.xueying.seeker.auth.client.resource.client")
@Import(FeignClientsConfiguration.class)
public class FeignClientConfig {

    /**
     * feign输出日志
     *
     * @return Logger.Level日志级别
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * oauth2拦截器
     *
     * @param oAuth2ProtectedResourceDetails oauth2配置
     * @return OauthFeign的请求
     */
    @Bean
    OAuth2FeignRequestInterceptor oauth2FeignRequestInterceptor(OAuth2ProtectedResourceDetails
                                                                        oAuth2ProtectedResourceDetails) {
        return new PlusOAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), oAuth2ProtectedResourceDetails);
    }

}