package com.zzr.springboot.service.async;

import java.util.concurrent.Future;

/**
 * 开发公司：山东海豚数据技术有限公司
 * 版权：山东海豚数据技术有限公司
 * <p>
 * ExecutorService
 *
 * @author zzr
 * @created Create Time: 2019/5/14
 */
public interface ExecutorService {

    Future<String> sendMsg() throws InterruptedException;
}
