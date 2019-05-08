package com.zzr.springboot.config.redis;

import com.zzr.springboot.controller.MyController;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    @Bean(name = "jedisPoolConfig")
    @ConfigurationProperties(prefix = "spring.redis.jedis.pool")
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    @Primary
    @Bean(name = "jedisConnectionFactory")
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setPoolConfig(jedisPoolConfig);
        return factory;
    }


    @Bean(name = "redisTemplate" )
    public RedisTemplate<?, ?> getRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<?, ?> redisTemplate = new StringRedisTemplate(jedisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer()); // key的序列化类型
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer()); // value的序列化类型
        return redisTemplate;
    }

    //初始化监听器
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter,
                                            MessageListenerAdapter listenerAdapter2,
                                            MessageListenerAdapter listenerAdapter3) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("key1"));//这里是监听的通道的名字
        container.addMessageListener(listenerAdapter2, new PatternTopic("key2"));
        container.addMessageListener(listenerAdapter3, new PatternTopic("key3"));
        return container;
    }

    //利用反射来创建监听到消息之后的执行方法
    @Bean
    MessageListenerAdapter listenerAdapter(MyController redisReceiver) {
        return new MessageListenerAdapter(redisReceiver, "receiveMessage");
    }

    @Bean
    MessageListenerAdapter listenerAdapter2(MyController redisReceiver) {
        return new MessageListenerAdapter(redisReceiver, "receiveMessage2");
    }

    @Bean
    MessageListenerAdapter listenerAdapter3(MyController redisReceiver) {
        return new MessageListenerAdapter(redisReceiver, "receiveMessage3");
    }
}