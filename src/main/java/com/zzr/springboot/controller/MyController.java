package com.zzr.springboot.controller;

import com.zzr.springboot.service.SendMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * 开发公司：山东海豚数据技术有限公司
 * 版权：山东海豚数据技术有限公司
 * <p>
 * MyController
 *
 * @author zzr
 * @created Create Time: 2019/4/25
 */
@RestController
@RequestMapping("/my")
@CrossOrigin
@Service
public class MyController {
    @Autowired
    public SendMsgService sendMsgService;

    @GetMapping("/sendMsg")
    public String sendMsg(String key,String msg){
        return sendMsgService.sendMessage(key, msg);
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
