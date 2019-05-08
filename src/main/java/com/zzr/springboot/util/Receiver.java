package com.zzr.springboot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.lang.Nullable;

/**
 * 开发公司：山东海豚数据技术有限公司
 * 版权：山东海豚数据技术有限公司
 * <p>
 * Receiver
 *
 * @author zzr
 * @created Create Time: 2019/4/25
 */
public class Receiver implements MessageListener {
    @Autowired
    public RedisTemplate redisTemplate;


    @Override
    public void onMessage(Message message, @Nullable byte[] bytes) {
        RedisSerializer<String> valueSerializer = redisTemplate.getStringSerializer();
        String deserialize = valueSerializer.deserialize(message.getBody());
        System.out.println("收到的mq消息" + deserialize);
    }
}
