/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: ScanResultResourceImpl
 * Author:   Allen
 * Date:     2019/7/17
 * Description: 获取扫描权限及Api信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.common.dto;

import lombok.Data;

/**
 * 客户端明细DTO
 *
 * @author Allen
 * @date 2019/7/17
 */
@Data
public class ClientDetailsDTO {
    /**
     * 用于客户端唯一标识 *
     */
    private String clientId;

    /**
     * 服务ID
     */
    private String resourceIds;

    /**
     * 客户端密码
     */
    private String clientSecret;

    /**
     * 客户端访问域
     */
    private String scope;

    /**
     * oauth授权类型
     */
    private String authorizedGrantTypes;

    /**
     * 跳转URI
     */
    private String webServerRedirectUri;

    /**
     * 角色编码，多个用“,”连接
     */
    private String authorities;

    /**
     * access_token初始超期时间
     */
    private String accessTokenValidity;

    /**
     * 刷新token初始超期时间
     */
    private String refreshTokenValidity;

    /**
     * 附加信息
     */
    private String additionalInformation;

    /**
     * 自动批准
     */
    private String autoapprove;

    /** 可绑定 **/
    private Boolean bind;
}
