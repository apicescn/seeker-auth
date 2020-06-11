/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: ClientQuery
 * Author:   Allen
 * Date:     2019/7/15
 * Description: 客户端信息上传入参
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.model.query;

import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * 客户端信息上传入参
 *
 * @author Allen
 * @date 2019-07-10
 */
@Data
public class ClientQuery {
    /**
     * 用于客户端唯一标识 *
     */
    @ApiParam(value = "客户端ID", example = "app")
    private String clientId;

    /**
     * 服务ID
     */
    @ApiParam(value = "服务ID", example = "openapi-service")
    private String resourceIds;

    /** 客户端密码 */
    @ApiParam(value = "客户端密码", example = "$10$7PtegA6OsLb402nVZ1C4oOTFvgE42kfnBuDuSGY")
    private String clientSecret;
    /**
     * 客户端访问域
     */
    @ApiParam(value = "客户端访问域", example = "read,write")
    private String scope;

    /**
     * oauth授权类型
     */
    @ApiParam(value = "授权类型", example = "authorization_code")
    private String authorizedGrantTypes;

    /** access_token初始超期时间 */
    private String accessTokenValidity;

}
