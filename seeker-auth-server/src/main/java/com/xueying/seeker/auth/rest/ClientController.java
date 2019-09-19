/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: ClientController
 * Author:   Allen
 * Date:     2019/7/15
 * Description: ClientController接口服务
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.rest;

import com.xueying.seeker.auth.model.dto.ClientDTO;
import com.xueying.seeker.auth.model.query.ClientQuery;
import com.xueying.seeker.auth.model.query.PageQuery;
import com.xueying.seeker.common.core.model.dto.RestDTO;
import com.xueying.seeker.common.core.model.dto.SimplePageVO;
import com.xueying.seeker.common.core.model.dto.SimpleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.client.ServiceInstance;

import java.util.List;
import java.util.Map;

/**
 * ClientController接口服务
 * @author Allen
 * @date 2019-07-10
 */
@Api(tags = {"dto-controller"})
public interface ClientController {
    /**
     * 根据ID查询客户端url
     */
    String GET_CLIENT_BY_ID = "/api/dto/id";
    /**
     * 根据ClientID查询客户端url
     */
    String GET_CLIENT_BY_CLIENTID = "/api/dto/clientId";
    /**
     * 获取客户端列表url
     */
    String GET_CLIENT_LIST = "/api/dto/clientList";
    /**
     * 客户端数据插入url
     */
    String INSERT_CLIENT = "/api/dto/insert";
    /**
     * 客户端数据更新url
     */
    String UPDATE_CLIENT = "/api/dto/update";
    /**
     * 根据客户端ID删除数据url
     */
    String DELETE_CLIENT = "/api/dto/delete";
    /**
     * 获取每一个服务下面实例服务ID
     */
    String SERVICE_NAME = "/api/dto/serviceName";
    /**
     * 根据主键ID查询客户端
     *
     * @param id ID
     * @return ClientDO
     */
    @ApiOperation(value = "根据主键ID查询客户端", notes = "根据主键ID查询客户端",
            protocols = "http,https", httpMethod = "GET")
    SimpleVO<ClientDTO> getClientById(@ApiParam(name = "id", value = "自增id", required = true) Long id
            , @ApiParam(name = "clientId", value = "客户端ID", required = true) String clientId);

    /**
     * 根据客户端ID查询客户端
     *
     * @param id 客户端ID
     * @return ClientDO
     */
    @ApiOperation(value = "根据客户端ID查询客户端", notes = "根据客户端ID查询客户端",
            protocols = "http,https", httpMethod = "GET")
    SimpleVO<ClientDTO> getClientByClientId(@ApiParam(name = "id", value = "客户端id", required = true) String id);

    /**
     * 根据查询条件查询客户端列表
     *
     * @param clientQuery 查询条件
     * @param pageQuery 分页条件
     * @return 客户端列表
     */
    @ApiOperation(value = "根据查询条件查询客户端列表", notes = "根据查询条件查询客户端列表",
            protocols = "http,https", httpMethod = "GET")
    SimplePageVO<List<ClientDTO>> listClientByPage(
            @ApiParam(name = "clientQuery", value = "查询条件", required = true) ClientQuery clientQuery,
            @ApiParam(name = "pageQuery", value = "分页条件") PageQuery pageQuery);

    /**
     * 添加客户端
     *
     * @param clientQuery 客户端信息
     * @return 操作结果
     */
    @ApiOperation(value = "添加客户端", notes = "添加客户端", protocols = "http,https", httpMethod = "POST")
    SimpleVO insertClient(@ApiParam(name = "clientQuery", value = "客户端信息", required = true) ClientQuery clientQuery);

    /**
     * 更新客户端信息
     * @param id 自增ID
     * @param clientQuery 客户端信息
     * @return 操作结果
     */
    @ApiOperation(value = "更新客户端信息", notes = "更新客户端信息", protocols = "http,https", httpMethod = "POST",
        response = RestDTO.class)
    SimpleVO updateClient(@ApiParam(name = "id", value = "ID", required = true) Long id,
            @ApiParam(name = "clientQuery", value = "客户端信息", required = true) ClientQuery clientQuery);

    /**
     * 删除客户端
     *
     * @param id 客户端Id
     * @return 操作结果
     */
    @ApiOperation(value = "删除客户端", notes = "删除客户端", protocols = "http,https", httpMethod = "POST",
        response = RestDTO.class)
    SimpleVO deleteClient(@ApiParam(name = "clientId", value = "客户端Id", required = true) String id);
    /**
     * 服务ID列表
     *
     * @return 服务ID列表
     */
    @ApiOperation(value = "服务ID列表", notes = "服务ID列表",
            protocols = "http,https", httpMethod = "GET")
    List<String> listServiceName();

}
