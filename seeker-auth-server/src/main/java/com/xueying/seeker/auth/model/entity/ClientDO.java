/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: ClientDO
 * Author:   Allen
 * Date:     2019/7/15
 * Description: 客户端信息实体
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 客户端信息实体
 *
 * @author Allen
 * @date 2019-07-10
 */
@Data
@TableName("oauth_client_details")
public class ClientDO implements Serializable {
    /**
     * 自增Id
     */
    @TableId(type = IdType.AUTO, value = "id")
    private Long id;

    /** 用于客户端唯一标识 */
    @TableField(value = "client_id")
    private String clientId;

    /** 服务ID */
    @TableField(value = "resource_ids")
    private String resourceIds;

    /** 客户端密码 */
    @TableField(value = "client_secret")
    private String clientSecret;

    /** 客户端访问域 */
    @TableField(value = "scope")
    private String scope;

    /** oauth授权类型 */
    @TableField(value = "authorized_grant_types")
    private String authorizedGrantTypes;

    /** 跳转URI */
    @TableField(value = "web_server_redirect_uri")
    private String webServerRedirectUri;

    /** 角色编码，多个用“,”连接 */
    @TableField(value = "authorities")
    private String authorities;

    /** access_token初始超期时间 */
    @TableField(value = "access_token_validity")
    private String accessTokenValidity;

    /** 刷新token初始超期时间 */
    @TableField(value = "refresh_token_validity")
    private String refreshTokenValidity;

    /** 附加信息 */
    @TableField(value = "additional_information")
    private String additionalInformation;

    /** 自动批准 */
    @TableField(value = "autoapprove")
    private String autoapprove;

    /** 可绑定 **/
    @TableField(value = "bind")
    private Boolean bind;
}
