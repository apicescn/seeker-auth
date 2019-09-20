/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: PageQuery
 * Author:   Allen
 * Date:     2019/5/21
 * Description: 通用分页入参
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.model.query;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 〈通用分页入参〉
 *
 * @author Allen
 * @create 2019/5/21
 * @since 1.0.0
 */
@Data
public class PageQuery implements Serializable {

    /**
     * 页数
     */
    @ApiParam(value = "页数", example = "1", required = true)
    @NotNull
    private Integer pageIndex;

    /**
     * 页码
     */
    @ApiParam(value = "页码", example = "10", required = true)
    @NotNull
    private Integer pageSize;
    /**
     * 开始页
     */
    @ApiParam(value = "开始页", example = "10")
    private Integer start;
    /**
     * 每页长度
     */
    @ApiParam(value = "每页长度", example = "10")
    private Integer length;
}
