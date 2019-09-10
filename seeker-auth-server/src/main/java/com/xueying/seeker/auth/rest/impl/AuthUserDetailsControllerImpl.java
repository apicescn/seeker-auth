/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: AuthUserDetailsControllerImpl
 * Author:   Allen
 * Date:     2019/7/15
 * Description: 获取用户详情信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.rest.impl;

import com.xueying.seeker.auth.config.feign.UserDetailsClientProperties;
import com.xueying.seeker.auth.rest.AuthUserDetailsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 获取用户详情信息
 *
 * @author Allen
 * @date 2019-07-10
 */
@RestController
public class AuthUserDetailsControllerImpl implements AuthUserDetailsController {

    /**
     * 客户端配置
     */
    @Autowired
    private UserDetailsClientProperties properties;

    /**
     *
     * @return List<FeignClientProperties>
     */
    @Override
    @GetMapping(value = GET_USER_DETAILS)
    public List<UserDetailsClientProperties.FeignClientProperties> userDetails() {
        return properties.getClients();
    }
}
