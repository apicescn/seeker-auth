/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: FeignConfig
 * Author:   Allen
 * Date:     2019/7/15
 * Description: feign配置信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.config.feign;

import com.xueying.seeker.auth.common.resource.UserDetailsResource;
import feign.Client;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * feign配置信息
 *
 * @author Allen
 * @date 2019/6/18
 */
@Configuration
@Import(FeignClientsConfiguration.class)
@EnableFeignClients
public class FeignServerConfig {
    /**
     * 客户端配置
     */
    @Autowired
    private UserDetailsClientProperties properties;
    /**
     * hashmap默认容量大小
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 6;

    /**
     * 根据user details配置创建feign客户端集合
     *
     * @param client fegin dto
     * @param interceptor oauth2拦截器
     * @return List<UserDetailsResource>
     */
    @Bean
    public Map<String, UserDetailsResource> userDetailsClients(Client client,
                                                               OAuth2FeignRequestInterceptor interceptor) {
        Map<String, UserDetailsResource> clients = new HashMap<>(DEFAULT_INITIAL_CAPACITY);
        Assert.notEmpty(properties.getClients(), "userdetails.clients没有配置");
        properties.getClients().forEach(p -> {
            Assert.notNull(p.getApplication(), "userdetails.clients.application没有配置");
            String contextPath = p.getContextPath() == null ? "" : "/" + p.getContextPath();
            String url = "http://" + p.getApplication() + contextPath;
            UserDetailsResource udc = Feign.builder().client(client).encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder()).requestInterceptor(interceptor).target(UserDetailsResource.class, url);
            if (p.getDefaultClient()) {
                p.setClientId(UserDetailsClientProperties.DEFAULT_CLIENT_ID);
            }
            Assert.isTrue(!clients.containsKey(p.getClientId()), "userdetails.clients.clientId配置重复");
            clients.put(p.getClientId(), udc);
        });
        return clients;
    }

    /**
     * oauth2拦截器
     *
     * @param oAuth2ProtectedResourceDetails oauth2配置
     * @return 拦截器
     */
    @Bean
    OAuth2FeignRequestInterceptor
        oauth2FeignRequestInterceptor(OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails) {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), oAuth2ProtectedResourceDetails);
    }

}
