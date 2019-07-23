/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: WebSecurityConfig
 * Author:   Allen
 * Date:     2019/7/15
 * Description: web安全配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.config.security;

import com.xueying.seeker.auth.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * web安全配置
 *
 * @author Allen
 * @date 2019-07-10
 */
@Configuration
@EnableWebSecurity
@Order(2)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 注入UserDetailsService实现类对象
     */
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * BCryptPasswordEncoder对象
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 创建认证管理器(不定义没有password grant_type,密码模式需要AuthenticationManager支持)
     *
     * @return AuthenticationManager对象
     * @throws Exception 异常信息
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 配置HttpSecurity
     *
     * @param http HttpSecurity对象
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // oauth的登录页面嵌入到其他frame页面，它们必须是同一个域
        http.headers().frameOptions().sameOrigin();
        // 关闭csrf
        http.csrf().disable();
        // ResourceServerConfig和WebSecurityConfig都必须配置关于登录页面的配置
        http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
    }

    /**
     * 配置AuthenticationManagerBuilder
     *
     * @param auth AuthenticationManagerBuilder对象
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 配置WebSecurity
     *
     * @param web WebSecurity对象
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 方便spring boot admin监控
        web.ignoring().antMatchers("/env/**", "/metrics/**", "/dump", "/jolokia/**", "/info", "/configprops", "/trace",
                "/logfile", "/refresh/**", "/flyway", "/heapdump", "/loggers/**", "/autoconfig", "/auditevents",
                "/hystrix.stream", "/health", "/bean", "/dist/**", "/webjars/**", "/swagger-ui.html",
                "/swagger-resources/**", "/v2/api-docs/**");
    }
}
