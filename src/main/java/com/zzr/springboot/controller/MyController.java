package com.zzr.springboot.controller;

import com.zzr.springboot.service.redis.SendMsgService;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 开发公司：山东海豚数据技术有限公司
 * 版权：山东海豚数据技术有限公司
 * <p>
 * MyController
 * 测试redis消息队列
 * @author zzr
 * @created Create Time: 2019/4/25
 */
@RestController
@RequestMapping("/my")
@CrossOrigin
@Service
public class MyController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public SendMsgService sendMsgService;
    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 添加list
     */
    @RequestMapping("/addList")
    public void addList(String str){
        for(int i = 0; i< 10; i++){
            redisTemplate.opsForList().rightPush(str,"v"+i);
        }
        logger.info("================添加list=============");
    }

    /**
     * 获取list
     * @param one
     * @param two
     * @return
     */
    @RequestMapping("/getList")
    public List getList(String str,Integer one,Integer two){
        List list = redisTemplate.opsForList().range(str,one,two);
        return list;
    }

    /**
     * 删除list元素
     * @param str
     * @param one
     */
    @RequestMapping("/rmList")
    public Long rmList(String str,Integer one,String value){
        Long i = redisTemplate.opsForList().remove(str,one,value);
        return i;
    }

    @GetMapping("/sendMsg")
    public void sendMsg(String key,String msg){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://47.95.117.206:6379").setClientName("clientName").setDatabase(1);
        RedissonClient redisson = Redisson.create(config);
        RLock redLock = redisson.getLock("REDLOCK_KEY");
        boolean isLock;
        try{
            isLock = redLock.tryLock();
            if(isLock){
                sendMsgService.sendMessage(key, msg);
            }else {
                System.out.println("锁被锁了");
            }
        }catch (Exception e){

        }finally {
            if(redLock.isHeldByCurrentThread()){
                redLock.unlock();
                System.out.println("关闭锁");
            }
        }

    }


    public void receiveMessage(String message) {
        System.out.println("key1 通道==="+message);
    }

    public void receiveMessage2(String message) {
        System.out.println("key2 通道==="+message);
    }

    public void receiveMessage3(String message) {
        System.out.println("key3 通道==="+message);
    }

}
