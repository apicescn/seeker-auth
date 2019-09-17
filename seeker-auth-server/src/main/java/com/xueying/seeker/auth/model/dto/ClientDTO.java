/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: ClientDTO
 * Author:   Allen
 * Date:     2019/7/15
 * Description: 客户端DTO
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 客户端DTO
 * @author Allen
 * @date 2019-07-10
 */
@Data
public class ClientDTO implements Serializable {
    /**
     * 自增ID *
     */
    @ApiModelProperty(value = "自增ID", example = "1", required = true, position = 0)
    private Long id;
    /**
     * 用于客户端唯一标识 *
     */
    @ApiModelProperty(value = "客户端ID", example = "app", required = true, position = 0)
    private String clientId;

    /**
     * 服务ID
     */
    @ApiModelProperty(value = "服务ID", example = "openapi-service", required = true, position = 1)
    private String resourceIds;

    /**
     * 客户端密码
     */
    @ApiModelProperty(value = "客户端密码", example = "apppassword", required = true, position = 2)
    private String clientSecret;

    /**
     * 客户端访问域
     */
    @ApiModelProperty(value = "客户端访问域", example = "read,write", required = true, position = 3)
    private String scope;

    /**
     * oauth授权类型
     */
    @ApiModelProperty(value = "oauth授权类型", example = "authorization_code", required = true, position = 4)
    private String authorizedGrantTypes;

    /**
     * 跳转URI
     */
    @ApiModelProperty(value = "跳转URI", example = "http://****", required = true, position = 5)
    private String webServerRedirectUri;

    /**
     * 角色编码，多个用“,”连接
     */
    @ApiModelProperty(value = "角色编码", example = "ADMIN,USER", required = true, position = 6)
    private String authorities;

    /**
     * access_token初始超期时间
     */
    @ApiModelProperty(value = "access_token初始超期时间", example = "31536000", required = true, position = 7)
    private String accessTokenValidity;

    /**
     * 刷新token初始超期时间
     */
    @ApiModelProperty(value = "刷新token初始超期时间", example = "31536000", required = true, position = 8)
    private String refreshTokenValidity;

    /**
     * 附加信息
     */
    @ApiModelProperty(value = "附加信息", example = "app客户端", required = true, position = 9)
    private String additionalInformation;

    /**
     * 自动批准
     */
    @ApiModelProperty(value = "自动批准", example = "auto", required = true, position = 10)
    private String autoapprove;

    /** 可绑定 **/
    @ApiModelProperty(value = "可绑定", example = "true", required = true, position = 10)
    private Boolean bind;
}
