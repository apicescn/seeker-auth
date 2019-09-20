/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: ApiDO
 * Author:   Allen
 * Date:     2019/9/19
 * Description: API实体
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

/**
 * API实体
 * @author Allen
 * @date 2019/9/19
 */
@Data
@TableName("oauth_service_api")
public class ApiDO implements Serializable {

    private static final long serialVersionUID = -2137319337385990610L;
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
    private Long id;

    /**
     * 标签
     */
    @TableField(value = "label")
    private String label;

    /**
     * 名称
     */
    @TableField(condition = SqlCondition.LIKE, value = "name")
    private String name;

    /**
     * 服务ID号，如：seeker-auth-server
     */
    @TableField(value = "serviceId")
    private String serviceId;

    /**
     * url地址
     */
    @TableField(value = "url")
    private String url;

    /**
     * 请求类型(POST,GET)
     */
    @TableField(value = "method")
    private String method;

    /**
     * API服务描述
     */
    @TableField(value = "description")
    private String description;

}
