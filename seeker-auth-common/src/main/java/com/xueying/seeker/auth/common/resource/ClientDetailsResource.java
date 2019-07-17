/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: ClientDetailsResource
 * Author:   Allen
 * Date:     2019/7/17
 * Description: 获取client_details信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.common.resource;

import com.xueying.seeker.auth.common.dto.ClientDetailsDTO;
import com.xueying.seeker.common.core.model.dto.SimpleVO;
import feign.Headers;
import feign.RequestLine;

import java.util.List;

/**
 * 获取client_details信息
 *
 * @author Allen
 * @date 2019/6/18
 */
public interface ClientDetailsResource {

    /**
     * 查询client_details url
     */
    String GET_CLIENT_DETAILS = "/api/dto/details";

    /**
     * 查询client_details信息
     * 
     * @return SimplePageDTO
     */
    @RequestLine("POST " + GET_CLIENT_DETAILS)
    @Headers("Content-Type: application/x-www-form-urlencoded")
    SimpleVO<List<ClientDetailsDTO>> getClientDetails();
}
