/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: ClientControllerImpl
 * Author:   Allen
 * Date:     2019/7/15
 * Description: 客户端rest接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.rest.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xueying.seeker.auth.model.dto.ClientDTO;
import com.xueying.seeker.auth.model.entity.ClientDO;
import com.xueying.seeker.auth.model.query.ClientQuery;
import com.xueying.seeker.auth.model.query.PageQuery;
import com.xueying.seeker.auth.rest.ClientController;
import com.xueying.seeker.auth.rest.constant.RestConstant;
import com.xueying.seeker.auth.service.ClientService;
import com.xueying.seeker.common.core.constant.CodeEnum;
import com.xueying.seeker.common.core.model.dto.SimplePageVO;
import com.xueying.seeker.common.core.model.dto.SimpleVO;
import com.xueying.seeker.common.util.SimpleConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 客户端rest接口
 *
 * @author Allen
 * @date 2019-07-10
 */
@Slf4j
@RestController
public class ClientControllerImpl implements ClientController {
    /**
     * clientService注入
     */
    @Autowired
    private ClientService clientService;
    /**
     * 获取每一个服务下面实例
     * 测试时，可以使用多个端口启动服务实例
     */
    @Autowired
    private DiscoveryClient discoveryClient;
    /**
     * 根据主键ID查询客户端
     * @param id ID
     * @return
     */
    @Override
    @GetMapping(value = GET_CLIENT_BY_ID, produces = {MediaType.APPLICATION_JSON_VALUE})
    public SimpleVO<ClientDTO> getClientById(@RequestParam(name = "id") Long id,
                                             @RequestParam(name = "clientId") String clientId) {
        ClientDO clientDO = clientService.getById(id, clientId);
        if (clientDO == null) {
            return new SimpleVO<>(CodeEnum.DATA_NOT_FOUND);
        }
        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(clientDO, clientDTO);
        return new SimpleVO<>(clientDTO);
    }

    /**
     * 根据客户端ID查询客户端
     *
     * @param id 客户端ID
     * @return 客户端信息
     */
    @GetMapping(value = GET_CLIENT_BY_CLIENTID, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public SimpleVO<ClientDTO> getClientByClientId(@RequestParam(name = "clientId", required = false) String id) {
        ClientDO clientDO = clientService.getByClientId(id);
        if (clientDO == null) {
            return new SimpleVO<>(CodeEnum.DATA_NOT_FOUND);
        }
        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(clientDO, clientDTO);
        return new SimpleVO<>(clientDTO);
    }

    /**
     * 根据条件查询客户端列表
     *
     * @param clientQuery 查询条件
     * @return 客户端信息
     */
    @Override
    @RequestMapping(value = GET_CLIENT_LIST, produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public SimplePageVO<List<ClientDTO>> listClientByPage(@Validated ClientQuery clientQuery, PageQuery pageQuery) {
        if (pageQuery.getPageIndex() == 0) {
            pageQuery.setPageIndex(RestConstant.DEFAULT_PAGE_INDEX);
        }
        if (pageQuery.getPageSize() == 0) {
            pageQuery.setPageSize(RestConstant.DEFAULT_PAGE_SIZE);
        }
        IPage<ClientDO> page = clientService.listByPage(clientQuery, pageQuery);
        List<ClientDO> clientDOList = page.getRecords();
        SimplePageVO<List<ClientDTO>> simplePageVO = new SimplePageVO<>(CodeEnum.DATA_NOT_FOUND);
        if (!CollectionUtils.isEmpty(clientDOList)) {
            List<ClientDTO> clientDTOList = SimpleConverter.convert(clientDOList, ClientDTO.class);
            simplePageVO = new SimplePageVO(clientDTOList, page);
        }
        return simplePageVO;
    }

    /**
     * 客户端新增
     *
     * @param clientQuery 客户端信息
     * @return 操作结果
     */
    @RequestMapping(value = INSERT_CLIENT, produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    @Override
    public SimpleVO insertClient(@Valid ClientQuery clientQuery) {
        SimpleVO simpleVO = new SimpleVO(CodeEnum.SUCCESS);
        Boolean result = clientService.insert(clientQuery);
        if (!result) {
            simpleVO = new SimpleVO(CodeEnum.INSERT_FAILED);
        }
        return simpleVO;
    }

    /**
     * 客户端更新
     *
     * @param id          id
     * @param clientQuery 客户端信息
     * @return 操作结果
     */
    @PostMapping(value = UPDATE_CLIENT, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public SimpleVO updateClient(@RequestParam("id") Long id,
                                 @Valid ClientQuery clientQuery) {
        SimpleVO simpleVO = new SimpleVO(CodeEnum.SUCCESS);
        Boolean result = clientService.update(id, clientQuery);
        if (!result) {
            simpleVO = new SimpleVO(CodeEnum.UPDATE_FAILED);
        }
        return simpleVO;
    }

    /**
     * 客户端删除
     *
     * @param id 客户端Id
     * @return 操作结果
     */
    @RequestMapping(value = DELETE_CLIENT, produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET)
    @Override
    public SimpleVO deleteClient(@RequestParam("clientId") String id) {
        SimpleVO simpleVO = new SimpleVO(CodeEnum.SUCCESS);
        Boolean result = clientService.removeByClientId(id);
        if (!result) {
            simpleVO = new SimpleVO(CodeEnum.DELETE_FAILED);
        }
        return simpleVO;
    }


    /**
     * <P>
     *     获取服务列表
     * </P>
     *
     * @return Map
     */
    @RequestMapping(value = SERVICE_NAME, produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    @Override
    public List<String> listServiceName() {
        List<String> services = discoveryClient.getServices();
        return services;
    }
}
