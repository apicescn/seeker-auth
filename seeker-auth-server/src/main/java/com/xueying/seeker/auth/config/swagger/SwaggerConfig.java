/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: SwaggerConfig
 * Author:   Allen
 * Date:     2019/7/15
 * Description: swagger2.0配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2.0配置
 *
 * @author Allen
 * @date 2019-07-10
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 创建Docket对象
     * 
     * @return 返回Docket对象
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建ApiInfo对象
     * 
     * @return 返回ApiInfo对象
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Seeker Cloud Auth").description("Auth Server接口文档").version("1.0").build();
    }
}
