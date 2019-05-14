package com.zzr.springboot.service.async.impl;

import com.zzr.springboot.service.async.ExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * 开发公司：山东海豚数据技术有限公司
 * 版权：山东海豚数据技术有限公司
 * <p>
 * ExecutorImpl
 *
 * @author zzr
 * @created Create Time: 2019/5/14
 */
@Service
public class ExecutorImpl implements ExecutorService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Async//@Async 表明是一个异步任务。
    public Future<String> sendMsg() throws InterruptedException {
        String url = "http://47.95.117.206:8667/rabbitmq/pushQueue?queueName=queue.one&content=dddddddddddddddd";
        ResponseEntity<String> result = restTemplate.getForEntity(url,String.class);
        Thread.sleep(1000L);
        return new AsyncResult<>(result.getBody());
    }
}
