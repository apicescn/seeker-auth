/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: ApiQuery
 * Author:   Allen
 * Date:     2019/9/19
 * Description: API VO
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.model.query;

import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * API VO
 *
 * @author Allen
 * @date 2019/9/19
 */
@Data
public class ApiQuery {

    /**
     * 标签
     */
    @ApiParam(value = "标签", example = "用户接口服务")
    private String label;

    /**
     * 名称
     */
    @ApiParam(value = "API名称", example = "用户服务")
    private String name;

    /**
     * 服务ID
     */
    @ApiParam(value = "服务ID", example = "服务ID")
    private String serviceId;

    /**
     * url地址
     */
    @ApiParam(value = "url", example = "/api/user/getById")
    private String url;

    /**
     * 请求类型(POST,GET)
     */
    @ApiParam(value = "请求类型", example = "POST")
    private String method;

}
