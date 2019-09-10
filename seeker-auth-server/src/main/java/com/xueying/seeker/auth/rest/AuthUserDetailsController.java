/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: AuthUserDetailsController
 * Author:   Allen
 * Date:     2019/7/15
 * Description: AuthUserDetailsController接口服务
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.rest;

import com.xueying.seeker.auth.config.feign.UserDetailsClientProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 获取用户信息
 *
 * @author Allen
 * @date 2019-07-10
 */
@Api(tags = {"auth-user-details-controller"})
public interface AuthUserDetailsController {

    /**
     * 查询UserDetails url
     */
    String GET_USER_DETAILS = "/api/userDetails/list";

    /**
     *
     * @return List<FeignClientProperties>
     */
    @ApiOperation(value = "用户详情信息查询", notes = "用户详情信息查询", protocols = "http,https", httpMethod = "GET")
    List<UserDetailsClientProperties.FeignClientProperties> userDetails();
}
