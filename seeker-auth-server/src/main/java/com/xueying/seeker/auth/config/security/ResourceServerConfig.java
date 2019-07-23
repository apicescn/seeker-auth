/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: ResourceServerConfig
 * Author:   Allen
 * Date:     2019/7/15
 * Description: 资源配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源配置
 *
 * @author Allen
 * @date 2019-07-10
 */
@Configuration
@EnableResourceServer
@Order(3)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    /**
     * 资源ID
     */
    private static final String RESOURCE_ID = "seeker-auth-server";

    /**
     * 配置资源ID
     *
     * @param resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }

    /**
     * 配置http
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // ResourceServerConfig和WebSecurityConfig都必须配置关于登录页面的配置
        http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll();
        http.authorizeRequests().antMatchers("/swagger**", "/v2/api-docs/**").permitAll()
                .anyRequest().authenticated();
    }
}