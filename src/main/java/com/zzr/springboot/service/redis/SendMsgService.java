package com.zzr.springboot.service.redis;

/**
 * 开发公司：山东海豚数据技术有限公司
 * 版权：山东海豚数据技术有限公司
 * <p>
 * SendMsgService
 *
 * @author zzr
 * @created Create Time: 2019/4/25
 */
public interface SendMsgService {
    String sendMessage(String key,String msg);
}
