/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: RefreshApiService
 * Author:   Allen
 * Date:     2019/9/19
 * Description: 动态刷入API接口类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过restTemplate来调用swagger接口数据动态刷入API接口数据
 * @author Allen
 * @date 2019/9/19
 */
@Slf4j
@Component
public class RefreshApiService {

    /**
     * 初始化RestTemplate
     * @return RestTemplate
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 引入DiscoveryClient
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     *  注入RestTemplate
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * API接口服务service服务
     */
    @Autowired
    private ApiService apiService;

    /**
     * 刷入api列表数据
     * @return 返回刷入是否成功
     */
    public Boolean refreshListApi() {
        Boolean result = true;
        List<ServiceInstance> listServiceInstance = new ArrayList<>();
        List<String> services = discoveryClient.getServices();
        services.forEach(service -> listServiceInstance.addAll(discoveryClient.getInstances(service)));
        for (ServiceInstance serviceInstance : listServiceInstance) {
            String hostIp = serviceInstance.getHost();
            String serviceName = serviceInstance.getServiceId();
            int servicePort = serviceInstance.getPort();
            log.debug("[服务列表信息] => host-->" + hostIp + ";serviceName-->" + serviceName + ";port-->" + servicePort);
            //TODO 此处需获得各个服务的contextPath，否则无法调用/v2/api-docs
            String contextPath;
            switch (serviceName) {
                case "xysy-seeker":
                    contextPath = "/seeker";
                    break;
                case "seeker-auth-server":
                    contextPath = "/uaa";
                    break;
                default:
                    contextPath = "/";
                    break;
            }
            String swaggerJson;
            try {
                String url = "http://" + serviceName + ":" + servicePort + contextPath + "/v2/api-docs";
                swaggerJson = restTemplate.getForEntity(url, String.class).getBody();
                log.debug("[调用swaggerApi地址为] =>" + url);
                apiService.refreshApi(swaggerJson, serviceInstance.getServiceId().toLowerCase());
            } catch (RestClientException error) {
                log.error("[未检测到swagger数据或contextPath异常，刷入api列表数据错误]：" + error);
                result = false;
            }
        }
        return result;
    }

}
