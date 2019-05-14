package com.zzr.springboot.controller;

import com.zzr.springboot.service.async.ExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/**
 * 开发公司：山东海豚数据技术有限公司
 * 版权：山东海豚数据技术有限公司
 * <p>
 * ExecutorController
 * 测试异步注解
 * @author zzr
 * @created Create Time: 2019/5/14
 */
@RestController
@RequestMapping("/executor")
public class ExecutorController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ExecutorService executorService;

    @RequestMapping("/textExecutor")
    public void textExecutor() throws Exception {

        // Start the clock
        long start = System.currentTimeMillis();

        Future<String> page1 = executorService.sendMsg();
        Future<String> page2 = executorService.sendMsg();
        Future<String> page3 = executorService.sendMsg();

        // Wait until they are all done
        while (!(page1.isDone() && page2.isDone() && page3.isDone())) {
            Thread.sleep(10); //10-millisecond pause between each check
        }

        // Print results, including elapsed time
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        logger.info("--> " + page1.get());
        logger.info("--> " + page2.get());
        logger.info("--> " + page3.get());
    }

}
