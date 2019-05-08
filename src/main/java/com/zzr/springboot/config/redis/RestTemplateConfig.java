package com.zzr.springboot.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 开发公司：青岛海豚数据技术有限公司
 * 版权：青岛海豚数据技术有限公司
 * <p>
 * RestTemplateConfig
 *
 * @author 刘志强
 * @created Create Time: 2019/3/25
 */

@Configuration
public class RestTemplateConfig {
    @Bean(name = "restTemplate")
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean(name = "factory")
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(15000);
        factory.setReadTimeout(5000);
        return factory;
    }
}