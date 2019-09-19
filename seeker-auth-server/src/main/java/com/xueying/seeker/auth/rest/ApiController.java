/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: ApiController
 * Author:   Allen
 * Date:     2019/9/19
 * Description: API接口controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.rest;

import com.xueying.seeker.auth.model.dto.ApiDTO;
import com.xueying.seeker.auth.model.query.ApiQuery;
import com.xueying.seeker.auth.model.query.PageQuery;
import com.xueying.seeker.common.core.model.dto.SimplePageVO;
import com.xueying.seeker.common.core.model.dto.SimpleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;

/**
 * @author Allen
 * @date 2019/9/19
 */
@Api(tags = {"api-controller"})
public interface ApiController {

    /**
     * 根据ID查询API接口信息的url
     */
    String GET_API_BY_ID = "/api/api/{id}";

    /**
     * 获取API接口列表url
     */
    String GET_API_LIST = "/api/api/list";
    /**
     * API接口数据插入url
     */
    String INSERT_API = "/api/api/insert";
    /**
     * API接口数据更新url
     */
    String UPDATE_API = "/api/api/update";
    /**
     * 根据ID删除数据url
     */
    String DELETE_API = "/api/api/delete";

    /**
     * 前端请求信息的url
     */
    String GET_API_REFRESH_AUTO = "/api/api/refresh/auto";

    /**
     * 根据ID查询API接口
     *
     * @param id API接口ID
     * @return ApiDO
     */
    @ApiOperation(value = "根据ID查询API接口", notes = "根据ID查询API接口", protocols = "http,https",
        httpMethod = "GET")
   SimpleVO<ApiDTO> getApiById(@ApiParam(name = "id", value = "API接口id", required = true) Long id);

    /**
     * 查询API接口列表
     *
     * @param apiQuery 参数
     * @param pageQuery 分页参数
     * @return API接口列表
     */
    @ApiOperation(value = "查询API接口列表", notes = "根据条件查询API接口列表",
            protocols = "http,https", httpMethod = "GET")
    SimplePageVO<List<ApiDTO>> getApiByPage(
            @ApiParam(name = "apiParam", value = "查询条件", required = true) ApiQuery apiQuery, PageQuery pageQuery);

    /**
     * 自动刷新API服务信息接口
     *
     * @return 操作结果
     */
    @ApiOperation(value = "自动刷新API服务信息接口", notes = "自动刷新API服务信息接口",
            protocols = "http,https", httpMethod = "GET")
    SimpleVO autoRefreshApi();

    /**
     * 添加API接口
     *
     * @param apiQuery API接口信息
     * @return 操作结果
     */
    @ApiOperation(value = "添加API接口", notes = "添加API接口", protocols = "http,https", httpMethod = "POST")
    SimpleVO insertApi(
            @ApiParam(name = "apiDO", value = "API接口信息", required = true) ApiQuery apiQuery);

    /**
     * 更新API接口信息
     *
     * @param apiQuery API接口信息
     * @return 操作结果
     */
    @ApiOperation(value = "更新API接口信息", notes = "更新API接口信息", protocols = "http,https", httpMethod = "POST")
    SimpleVO updateApi(
            @ApiParam(name = "apiDO", value = "API接口信息", required = true) ApiQuery apiQuery);

    /**
     * 删除API接口
     *
     * @param id API接口Id
     * @return 操作结果
     */
    @ApiOperation(value = "删除API接口", notes = "删除API接口", protocols = "http,https", httpMethod = "POST")
    SimpleVO deleteApi(@ApiParam(name = "id", value = "API接口Id", required = true) Long id);
}
