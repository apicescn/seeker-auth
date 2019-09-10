/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: WebMvcConfig
 * Author:   Allen
 * Date:     2019/7/15
 * Description: spring mvc配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * spring mvc配置
 *
 * @author Allen
 * @date 2019-07-10
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 注册绑定/login和login.ftl视图模板
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/welcome.html").setViewName("welcome");
        registry.addViewController("/welcome_iframe.html").setViewName("welcome_iframe");
    }
}
