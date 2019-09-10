/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: ClientDAO
 * Author:   Allen
 * Date:     2019/7/15
 * Description: ClientDO实体数据操作对象
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xueying.seeker.auth.model.entity.ClientDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClientDO实体数据操作对象
 *
 * @author Allen
 * @date 2019-07-10
 */
@Mapper
public interface ClientDAO extends BaseMapper<ClientDO> {
}
