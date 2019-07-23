/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: UserDetailsClientProperties
 * Author:   Allen
 * Date:     2019/7/15
 * Description: 用户信息配置文件
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.config.feign;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 〈用户信息配置文件〉
 *
 * @author Allen
 * @create 2019/5/24
 * @since 1.0.0
 */
@Data
@ConfigurationProperties(prefix = "userdetails")
public class UserDetailsClientProperties {
    /**
     * 默认clientId
     */
    public static final String DEFAULT_CLIENT_ID = "admin";
    /**
     * 获取user details的客户端
     */
    private List<FeignClientProperties> clients;

    /**
     * oauth客户端配置,通过clientId绑定userdetails数据源
     */
    @Data
    public static class FeignClientProperties {
        /**
         * clientId
         */
        private String clientId;
        /**
         * 描述
         */
        private String desc;
        /**
         * 应用名称
         */
        private String application;
        /**
         * 默认客户端
         */
        private Boolean defaultClient = false;
    }
}
