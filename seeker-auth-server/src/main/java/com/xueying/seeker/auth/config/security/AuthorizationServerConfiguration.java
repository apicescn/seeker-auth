/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: Auth2JwtConfig
 * Author:   Allen
 * Date:     2019/7/15
 * Description: oauth2.0配置，token以jwt形式进行存储
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.config.security;

import com.xueying.seeker.auth.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * oauth2.0配置，token以jwt形式进行存储
 *
 * @author Allen
 * @date 2019-07-10
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    /**
     * Redis连接工厂
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    /**
     * 默认签名key
     */
    private String signingKey = "M3NvbmdzaHUuY29t";

    /**
     * 数据源
     */
    @Autowired
    private DataSource dataSource;
    /**
     * 注入UserDetailsService实现类对象
     */
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * 注入认证管理器
     */
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    /**
     * 存储code,涉及表为oauth_code
     *
     * @return AuthorizationCodeServices
     */
    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * 保存批准信息approval,涉及表oauth_approvals,主要是保存读写等scope的期限
     *
     * @return ApprovalStore
     */
    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    /**
     * 客户端信息存储在oauth_client_details表中
     *
     * @param clients ClientDetailsServiceConfigurer对象
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).clients(this.clientDetails());
    }

    /**
     * 配置
     *
     * @param endpoints AuthorizationServerEndpointsConfigurer对象
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authorizationCodeServices(authorizationCodeServices()).approvalStore(approvalStore())
                .tokenStore(tokenStore()).tokenEnhancer(jwtTokenEnhancer()).userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager);
    }

    /**
     * jwt token存储实现类
     *
     * @return TokenStore对象
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * 声明 ClientDetails实现(直接从数据库中去获得)
     * @return ClientDetailsService
     */
    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }
    /**
     * jwt转换器
     *
     * @return JwtAccessTokenConverter
     */
    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(signingKey);
        return jwtAccessTokenConverter;
    }

    /**
     * 设置签名key
     *
     * @param signingKey 签名key
     */
    public void setSigningKey(String signingKey) {
        this.signingKey = signingKey;
    }

    /**
     * 对/oauth/token_key和/oauth/check_token访问权限的控制
     *
     * @param security AuthorizationServerSecurityConfigurer对象
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // 默认为denyAll()
                .tokenKeyAccess("permitAll()")
                // 默认为denyAll()
                .checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
    }
}
