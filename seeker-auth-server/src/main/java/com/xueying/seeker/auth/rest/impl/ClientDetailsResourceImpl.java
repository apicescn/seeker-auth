/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: ClientDetailsResourceImpl
 * Author:   Allen
 * Date:     2019/7/15
 * Description: 获取client_details信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.rest.impl;

import com.xueying.seeker.auth.common.dto.ClientDetailsDTO;
import com.xueying.seeker.auth.common.resource.ClientDetailsResource;
import com.xueying.seeker.auth.model.entity.ClientDO;
import com.xueying.seeker.auth.service.ClientService;
import com.xueying.seeker.common.core.constant.CodeEnum;
import com.xueying.seeker.common.core.model.dto.SimpleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取client_details信息
 *
 * @author Allen
 * @date 2019-07-10
 */
@Slf4j
@RestController
public class ClientDetailsResourceImpl implements ClientDetailsResource {

    /**
     * clientService注入
     */
    @Autowired
    private ClientService clientService;

    /**
     * 查询client_details信息
     *
     * @return SimplePageDTO
     */
    @PostMapping(value = GET_CLIENT_DETAILS, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public SimpleVO<List<ClientDetailsDTO>> getClientDetails() {
        try {
            List<ClientDO> detailsDTOList = clientService.getClientDetails();
            SimpleVO<List<ClientDetailsDTO>> simpleDTO = new SimpleVO<>(CodeEnum.SUCCESS);
            List<ClientDetailsDTO> results = new ArrayList<>();
            if (!CollectionUtils.isEmpty(detailsDTOList)) {
                detailsDTOList.stream().forEach(u -> {
                    ClientDetailsDTO clientDetailsDTO = new ClientDetailsDTO();
                    BeanUtils.copyProperties(u, clientDetailsDTO);
                    results.add(clientDetailsDTO);
                });
            }
            simpleDTO.setData(results);
            return simpleDTO;
        } catch (Exception e) {
            log.error("getClientDetails() 全部查询客户端接口异常 Exception. ErrorMsg:{}", e.getMessage(), e);
            SimpleVO<List<ClientDetailsDTO>> simpleDTO = new SimpleVO<>(CodeEnum.UNKNOWN_ERROR);
            return simpleDTO;
        }
    }
}
