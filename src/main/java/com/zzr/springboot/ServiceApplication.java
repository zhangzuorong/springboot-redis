package com.zzr.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 开发公司：山东海豚数据技术有限公司
 * 版权：山东海豚数据技术有限公司
 * <p>
 * ServiceApplication
 *
 * @author zzr
 * @created Create Time: 2019/4/25
 */
@SpringBootApplication
@EnableAsync //开启异步任务
public class ServiceApplication extends AsyncConfigurerSupport implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("服务器启动");
    }

    //配置AsyncConfigurerSupport
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);//线程池维护线程的最少数量
        executor.setMaxPoolSize(3);//线程池维护线程的最大数量
        executor.setQueueCapacity(500);//缓存队列
        executor.setThreadNamePrefix("MyExecutor-");//线程名称前缀
        executor.initialize();
        return executor;
    }
}
