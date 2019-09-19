/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: ApiDTO
 * Author:   Allen
 * Date:     2019/9/19
 * Description: API返回DTO
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * API返回DTO
 * @author Allen
 * @since 2019/9/19
 */
@Setter
@Getter
@ToString
public class ApiDTO implements Serializable {

    private static final long serialVersionUID = 1939578358396710032L;
    /**
     * ID
     */
    @ApiModelProperty(value = "ID", example = "1", required = true)
    private Long id;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签", example = "用户服务", required = true, position = 1)
    private String label;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", example = "用户服务", required = true, position = 2)
    private String name;

    /**
     * 服务ID
     */
    @ApiModelProperty(value = "服务ID", example = "服务ID", required = true, position = 3)
    private String serviceId;

    /**
     * url地址
     */
    @ApiModelProperty(value = "url", example = "/user/getById", required = true, position = 4)
    private String url;

    /**
     * 请求类型(POST,GET)
     */
    @ApiModelProperty(value = "请求类型", example = "POST", required = true, position = 5)
    private String method;

    /**
     * 角色描述
     */
    @ApiModelProperty(value = "服务描述", example = "服务描述", position = 6)
    private String description;

}
