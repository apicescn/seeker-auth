/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: ApiDAO
 * Author:   Allen
 * Date:     2019/9/19
 * Description: API接口操作DAO
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xueying.seeker.auth.model.entity.ApiDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * API接口操作DAO
 * @author Allen
 * @date 2019/9/19
 *       <p>
 *       RoleDO实体数据操作对象
 */
@Mapper
public interface ApiDAO extends BaseMapper<ApiDO> {

}
