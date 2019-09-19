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
public class ApiDO extends BaseDO implements Serializable {

    private static final long serialVersionUID = -2137319337385990610L;
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标签
     */
    private String label;

    /**
     * 名称
     */
    @TableField(condition = SqlCondition.LIKE)
    private String name;

    /**
     * 服务ID号，如：marge-admin-server
     */
    private String serviceId;

    /**
     * url地址
     */
    private String url;

    /**
     * 请求类型(POST,GET)
     */
    private String method;

    /**
     * 角色描述
     */
    private String description;

}
