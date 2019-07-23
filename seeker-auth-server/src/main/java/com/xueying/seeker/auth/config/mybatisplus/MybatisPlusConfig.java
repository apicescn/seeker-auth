/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: MybatisPlusConfig
 * Author:   Allen
 * Date:     2019/7/15
 * Description: MybatisPlus分页配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.config.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** MybatisPlus分页配置
 * @author Allen
 * @date 2019-07-10
 */
@Configuration
@MapperScan("com.xueying.seeker.auth.dao")
public class MybatisPlusConfig {

    /**
     * 分页插件
     * 
     * @return 分页
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}