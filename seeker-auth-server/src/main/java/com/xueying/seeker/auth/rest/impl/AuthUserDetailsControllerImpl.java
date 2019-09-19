/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: AuthUserDetailsControllerImpl
 * Author:   Allen
 * Date:     2019/7/15
 * Description: 获取用户详情信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.rest.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xueying.seeker.auth.config.feign.UserDetailsClientProperties;
import com.xueying.seeker.auth.rest.AuthUserDetailsController;
import com.xueying.seeker.common.core.constant.CodeEnum;
import com.xueying.seeker.common.core.model.dto.SimplePageVO;
import com.xueying.seeker.common.util.SimpleConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 获取用户详情信息
 *
 * @author Allen
 * @date 2019-07-10
 */
@Controller
public class AuthUserDetailsControllerImpl implements AuthUserDetailsController {

    /**
     * 客户端配置
     */
    @Autowired
    private UserDetailsClientProperties properties;

    /**
     * 查询UserDetails
     * @return List<FeignClientProperties>
     */
    @Override
    @RequestMapping(value = GET_USER_DETAILS, method = RequestMethod.POST)
    @ResponseBody
    public SimplePageVO<List<UserDetailsClientProperties.FeignClientProperties>> userDetails() {
        SimplePageVO<List<UserDetailsClientProperties.FeignClientProperties>> simplePageVO =
                new SimplePageVO<>(CodeEnum.DATA_NOT_FOUND);
        List<UserDetailsClientProperties.FeignClientProperties> list = properties.getClients();
        if (!CollectionUtils.isEmpty(list)) {
            List<UserDetailsClientProperties.FeignClientProperties> clientDTOList =
                    SimpleConverter.convert(list, UserDetailsClientProperties.FeignClientProperties.class);
            IPage page = new Page();
            page.setSize(list.size());
            page.setCurrent(1);
            page.setTotal(list.size());
            simplePageVO = new SimplePageVO(clientDTOList, page);
        }
        return  simplePageVO;
    }
}
