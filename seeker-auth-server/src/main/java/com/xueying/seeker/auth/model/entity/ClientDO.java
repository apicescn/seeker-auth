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

    /** 用于客户端唯一标识 */
    @TableId
    private String clientId;

    /** 服务ID */
    private String resourceIds;

    /** 客户端密码 */
    private String clientSecret;

    /** 客户端访问域 */
    private String scope;

    /** oauth授权类型 */
    private String authorizedGrantTypes;

    /** 跳转URI */
    private String webServerRedirectUri;

    /** 角色编码，多个用“,”连接 */
    private String authorities;

    /** access_token初始超期时间 */
    private String accessTokenValidity;

    /** 刷新token初始超期时间 */
    private String refreshTokenValidity;

    /** 附加信息 */
    private String additionalInformation;

    /** 自动批准 */
    private String autoapprove;

    /** 可绑定 **/
    private Boolean bind;
}
