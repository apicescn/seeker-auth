package com.xueying.seeker.auth.model.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * API VO
 *
 * @author 鼠良帅
 * @date 2018/3/7
 */
@Data
public class ApiQuery {

    /**
     * ID
     */
    @ApiModelProperty(value = "ID", example = "1", required = true)
    private Long id;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签", example = "用户接口服务", required = true, position = 1)
    private String label;

    /**
     * 名称
     */
    @ApiModelProperty(value = "API名称", example = "用户服务", required = true, position = 2)
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
     * 描述
     */
    @ApiModelProperty(value = "服务描述", example = "服务描述", position = 6)
    private String description;

    /**
     * 是否启用：0-不可用，1-可用
     */
    @ApiModelProperty(value = "是否启用", example = "0", required = true, position = 7)
    private Boolean enabled;

    /**
     * 删除标示：0-未删除，1-已删除
     */
    @ApiModelProperty(value = "删除标示", example = "0", position = 8)
    private Boolean deleted;

}
