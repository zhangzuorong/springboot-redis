package com.zzr.springboot.service.impl;

import com.zzr.springboot.service.SendMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 开发公司：山东海豚数据技术有限公司
 * 版权：山东海豚数据技术有限公司
 * <p>
 * SendMsgImpl
 *
 * @author zzr
 * @created Create Time: 2019/4/25
 */
@Service
public class SendMsgImpl implements SendMsgService {

    @Autowired
    public RedisTemplate redisTemplate;

    @Override
    public String sendMessage(String key, String msg) {
        try {
            redisTemplate.convertAndSend(key, msg);
            return "消息发送成功了";

        } catch (Exception e) {
            e.printStackTrace();
            return "消息发送失败了";
        }
    }
}
