/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: AuthCoreConfig
 * Author:   Allen
 * Date:     2019/7/15
 * Description: auth配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.config.feign;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * auth配置
 *
 * @author Allen
 * @date 2019-07-10
 * @since 1.0
 */
@Configuration
@EnableConfigurationProperties(UserDetailsClientProperties.class)
public class AuthCoreConfig {
}
