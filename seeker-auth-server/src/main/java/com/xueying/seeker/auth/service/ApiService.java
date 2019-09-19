/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: ApiService
 * Author:   Allen
 * Date:     2019/9/19
 * Description: API接口服务
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.service;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xueying.seeker.auth.dao.ApiDAO;
import com.xueying.seeker.auth.model.entity.ApiDO;
import com.xueying.seeker.auth.model.query.ApiQuery;
import com.xueying.seeker.auth.model.query.PageQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * API接口服务
 *
 * @author Allen
 * @date 2019/9/19
 */
@Service
public class ApiService extends ServiceImpl<ApiDAO, ApiDO> {

    /**
     * 新增API接口信息
     * @param apiQuery API接口参数
     * @return 是否成功
     */
    public Boolean insert(ApiQuery apiQuery) {
        ApiDO apiDO = new ApiDO();
        BeanUtils.copyProperties(apiQuery, apiDO);
        apiDO.setDateCreated(new Date());
        return save(apiDO);
    }

    /**
     * 更新API接口信息
     * @param apiQuery API接口参数
     * @return 是否成功
     */
    public Boolean update(ApiQuery apiQuery) {
        ApiDO apiDO = getById(apiQuery.getId());
        BeanUtils.copyProperties(apiQuery, apiDO);
        apiDO.setLastModified(new Date());
        return updateById(apiDO);
    }

    /**
     * 查询API接口列表(分页)
     *
     * @param apiQuery API接口参数
     * @param pageQuery 分页参数
     * @return 查询API接口分页列表
     */
    public IPage<ApiDO> selectListPage(ApiQuery apiQuery, PageQuery pageQuery) {
        ApiDO apiDO = new ApiDO();
        BeanUtils.copyProperties(apiQuery, apiDO);
        Wrapper<ApiDO> eWrapper = new QueryWrapper<>(apiDO);
        IPage<ApiDO> page = page(new Page<>(pageQuery.getPageIndex(), pageQuery.getPageSize()), eWrapper);
        return page;
    }

    /**
     * 获取swagger生成的接口服务信息，并将相关信息保存至数据库。
     * @param swaggerJson swagger的Json数据内容
     * @param serviceId serviceId
     * @return 信息保存成功或失败
     */
    public Boolean refreshApi(String swaggerJson, String serviceId) {
        Boolean success = false;
        if (!swaggerJson.isEmpty()) {
            try {
                JSONObject json = JSONObject.parseObject(swaggerJson);
                String paths = json.getString("paths");
                JSONObject pathJson = JSONObject.parseObject(paths);
                Set<Entry<String, Object>> pathSet = pathJson.entrySet();
                for (Entry pathEntry : pathSet) {
                    String url = pathEntry.getKey().toString();
                    Object value = pathEntry.getValue();
                    JSONObject jsonPath = JSONObject.parseObject(value.toString());
                    Set<Entry<String, Object>> methods = jsonPath.entrySet();
                    Entry apiInfo = methods.iterator().next();
                    String method = apiInfo.getKey().toString();
                    Object info = apiInfo.getValue();

                    String label = ((Map)info).get("operationId").toString();
                    String name = ((Map)info).get("summary").toString();

                    String description
                        = ((Map)info).get("description") == null ? name : ((Map)info).get("description").toString();

                    ApiQuery apiQuery = new ApiQuery();
                    apiQuery.setLabel(label);
                    apiQuery.setName(name);
                    apiQuery.setMethod(method.toUpperCase());
                    apiQuery.setUrl(url);
                    apiQuery.setServiceId(serviceId);
                    apiQuery.setDescription(description);

                    ApiDO apiDO = new ApiDO();
                    apiDO.setUrl(apiQuery.getUrl());
                    apiDO.setServiceId(apiQuery.getServiceId());
                    Wrapper<ApiDO> eWrapper = new QueryWrapper<>(apiDO);
                    int count = count(eWrapper);
                    if (count > 0) {
                        ApiDO oldApiDO = getOne(eWrapper);
                        apiQuery.setId(oldApiDO.getId());
                        success = update(apiQuery);
                    } else {
                        success = insert(apiQuery);
                    }
                }
            } catch (JSONException error) {
                throw new JSONException("JSON格式解析错误-->" + error + "；\n JSON数据为-->" + swaggerJson);
            }
        }
        return success;
    }


    /**
     * 查询所有的API
     *
     * @return 所有的API
     */
    public List<ApiDO> findAll() {
        Wrapper<ApiDO> eWrapper = new QueryWrapper<>();
        List<ApiDO> apiDOList = list(eWrapper);
        return apiDOList;

    }

}
