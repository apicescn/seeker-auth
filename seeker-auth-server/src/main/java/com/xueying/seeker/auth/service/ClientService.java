/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: ClientService
 * Author:   Allen
 * Date:     2019/7/15
 * Description: Client服务类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xueying.seeker.auth.dao.ClientDAO;
import com.xueying.seeker.auth.model.entity.ClientDO;
import com.xueying.seeker.auth.model.query.ClientQuery;
import com.xueying.seeker.auth.model.query.PageQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户端服务
 *
 * @author Allen
 * @date 2019/6/18
 */
@Service
public class ClientService extends ServiceImpl<ClientDAO, ClientDO> {
    /**
     * 新增客户端信息
     *
     * @param clientQuery 客户端信息
     * @return 插入记录
     */
    public Boolean insert(ClientQuery clientQuery) {
        ClientDO clientDO = new ClientDO();
        BeanUtils.copyProperties(clientQuery, clientDO);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String bcryPassword = bCryptPasswordEncoder.encode(clientDO.getClientSecret());
        clientDO.setClientSecret(bcryPassword);
        // TODO 获取当前登录用户的信息，并将该登录用户信息添加到插入客户端的创建属性中
        return save(clientDO);
    }

    /**
     * 更新客户端信息
     *
     * @param clientQuery 客户端信息
     * @return 更新记录
     */
    public Boolean update(Long id, ClientQuery clientQuery) {
        QueryWrapper<ClientDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(true, "client_id", clientQuery.getClientId());
        ClientDO clientDO = getOne(queryWrapper);
        if (clientDO == null) {
            clientDO = new ClientDO();
        }
        BeanUtils.copyProperties(clientQuery, clientDO);
        clientDO.setId(id);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String bcryPassword = bCryptPasswordEncoder.encode(clientDO.getClientSecret());
        clientDO.setClientSecret(bcryPassword);
        // TODO 获取当前登录用户的信息，并将该登录用户信息添加到更新客户端的更新属性中
        return updateById(clientDO);
    }
    /**
     * 分页查询
     *
     * @param clientQuery 查询参数
     * @param pageQuery 分页参数
     * @return Page<ClientDO> 分页数据
     */
    public IPage<ClientDO> listByPage(ClientQuery clientQuery, PageQuery pageQuery) {
        ClientDO clientDO = new ClientDO();
        BeanUtils.copyProperties(clientQuery, clientDO);
        Wrapper<ClientDO> wrapper = new QueryWrapper(clientDO);
        IPage<ClientDO> page = page(new Page<>(pageQuery.getPageIndex(),
                pageQuery.getPageSize()), wrapper);
        return page;
    }

    /**
     * 全部查询
     *
     * @return List<ClientDetailsDTO>
     */
    public List<ClientDO> getClientDetails() {
        ClientDO clientDO = new ClientDO();
        clientDO.setAuthorizedGrantTypes("client_credentials");
        QueryWrapper<ClientDO> wrapper = new QueryWrapper<>(clientDO);
        List<ClientDO> clientDOList = list(wrapper);
        return clientDOList;
    }

    /**
     * 根据clientId删除
     * @param clientId clientId
     * @return
     */
    public Boolean removeByClientId(String clientId){
        QueryWrapper<ClientDO> qwrapper = new QueryWrapper<>();
        qwrapper.eq(true, "client_id", clientId);
        return remove(qwrapper);
    }

    /**
     * 根据clientId获取详情
     * @param clientId clientId
     * @return
     */
    public ClientDO getByClientId(String clientId){
        QueryWrapper<ClientDO> qwrapper = new QueryWrapper<>();
        qwrapper.eq(true, "client_id", clientId);
        return getOne(qwrapper);
    }

    /**
     * 根据Id、clientId判断ClientID是否存在
     * @param id 主键ID
     * @param ClientId 客户端ID
     * @return Boolean
     */
    public ClientDO getById(Long id, String ClientId){
        QueryWrapper<ClientDO> qwrapper = new QueryWrapper<>();
        qwrapper.ne(true, "id", id)
                .eq(true, "client_id", ClientId);
        return getOne(qwrapper);
    }
}
