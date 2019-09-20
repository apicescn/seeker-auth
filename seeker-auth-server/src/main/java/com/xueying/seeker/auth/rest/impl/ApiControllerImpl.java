/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: ApiControllerImpl
 * Author:   Allen
 * Date:     2019/9/19
 * Description: API接口rest接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.rest.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xueying.seeker.auth.model.dto.ApiDTO;
import com.xueying.seeker.auth.model.entity.ApiDO;
import com.xueying.seeker.auth.model.query.ApiQuery;
import com.xueying.seeker.auth.model.query.PageQuery;
import com.xueying.seeker.auth.rest.ApiController;
import com.xueying.seeker.auth.service.ApiService;
import com.xueying.seeker.auth.service.RefreshApiService;
import com.xueying.seeker.common.core.constant.CodeEnum;
import com.xueying.seeker.common.core.model.dto.SimplePageVO;
import com.xueying.seeker.common.core.model.dto.SimpleVO;
import com.xueying.seeker.common.util.SimpleConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.xueying.seeker.auth.rest.constant.RestConstant.DEFAULT_PAGE_SIZE;

/**
 * API接口rest接口
 *
 * @author Allen
 * @date 2019/9/19
 */
@Slf4j
@RestController
public class ApiControllerImpl implements ApiController {

    /**
     * apiService注入
     */
    @Autowired
    private ApiService apiService;

    /**
     * RefreshApiService注入
     */
    @Autowired
    private RefreshApiService refreshApiService;

    /**
     * 根据ID查询API接口
     *
     * @param id API接口ID
     * @return API接口信息
     */
    @GetMapping(value = GET_API_BY_ID, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public SimpleVO<ApiDTO> getApiById(@PathVariable("id") Long id) {
        ApiDO apiDO = apiService.getById(id);
        if (apiDO == null) {
            return new SimpleVO<>(CodeEnum.DATA_NOT_FOUND);
        }
        ApiDTO apiDTO = new ApiDTO();
        BeanUtils.copyProperties(apiDO, apiDTO);
        return new SimpleVO<>(apiDTO);
    }

    /**
     * 查询API接口列表
     *
     * @param apiQuery  参数
     * @param pageQuery 分页参数
     * @return API接口列表
     */
    @RequestMapping(value = GET_API_LIST, produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    @Override
    public SimplePageVO<List<ApiDTO>> getApiByPage(@Valid ApiQuery apiQuery, PageQuery pageQuery) {
        if (pageQuery.getStart() != null) {
            pageQuery.setPageIndex(pageQuery.getStart() / DEFAULT_PAGE_SIZE + 1);
        }
        if (pageQuery.getLength() != null) {
            pageQuery.setPageSize(pageQuery.getLength());
        }

        IPage<ApiDO> page = apiService.selectListPage(apiQuery, pageQuery);
        List<ApiDO> apiDOList = page.getRecords();
        SimplePageVO<List<ApiDTO>> simplePageVO = new SimplePageVO<>(CodeEnum.DATA_NOT_FOUND);
        if (!CollectionUtils.isEmpty(apiDOList)) {
            List<ApiDTO> apiDTOList = SimpleConverter.convert(apiDOList, ApiDTO.class);
            simplePageVO = new SimplePageVO(apiDTOList, page);
        }
        return simplePageVO;
    }

    /**
     * 自动刷新API服务信息接口
     *
     * @return 数据刷新是否成功
     */
    @RequestMapping(value = GET_API_REFRESH_AUTO, produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST)
    @Override
    public SimpleVO autoRefreshApi() {
        SimpleVO simpleVO = new SimpleVO(CodeEnum.SUCCESS);
        Boolean success = refreshApiService.refreshListApi();
        if (!success) {
            simpleVO = new SimpleVO(CodeEnum.DATA_NOT_FOUND);
        }
        return simpleVO;
    }

    /**
     * API接口新增
     *
     * @param apiQuery API接口信息
     * @return API接口信息
     */
    @PostMapping(value = INSERT_API, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public SimpleVO insertApi(@Valid ApiQuery apiQuery) {
        SimpleVO simpleVO = new SimpleVO(CodeEnum.SUCCESS);
        Boolean result = apiService.insert(apiQuery);
        if (!result) {
            simpleVO = new SimpleVO(CodeEnum.INSERT_FAILED);
        }
        return simpleVO;
    }

    /**
     * API接口更新
     *
     * @param apiQuery API接口信息
     * @return API接口信息
     */
    @PostMapping(value = UPDATE_API, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public SimpleVO updateApi(@Valid ApiQuery apiQuery) {
        SimpleVO simpleVO = new SimpleVO(CodeEnum.SUCCESS);
        Boolean result = apiService.update(apiQuery);
        if (!result) {
            simpleVO = new SimpleVO(CodeEnum.UPDATE_FAILED);
        }
        return simpleVO;
    }

    /**
     * API接口删除
     *
     * @param id API接口Id
     * @return 删除API接口
     */
    @PostMapping(value = DELETE_API, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public SimpleVO deleteApi(@RequestParam("id") Long id) {
        SimpleVO simpleVO = new SimpleVO(CodeEnum.SUCCESS);
        Boolean result = apiService.removeById(id);
        if (!result) {
            simpleVO = new SimpleVO(CodeEnum.DELETE_FAILED);
        }
        return simpleVO;
    }
}
