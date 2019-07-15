/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: AuthServerApplication
 * Author:   Allen
 * Date:     2019/7/15
 * Description: AuthServer的启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * AuthServer的启动类
 *
 * @author Allen
 * @date 2019-07-15
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class AuthServerApplication {
    /**
     * 主方法
     *
     * @param args spring boot参数
     */
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }
}
