package com.zzr.springboot.controller;

import com.zzr.springboot.config.aop.Monitor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开发公司：山东海豚数据技术有限公司
 * 版权：山东海豚数据技术有限公司
 * <p>
 * AopController
 * 测试aop
 * @author zzr
 * @created Create Time: 2019/5/13
 */
@RestController
@RequestMapping("/aop")
public class AopController {

    @GetMapping("index")
    @ResponseBody
    public String index(String userName){
        return "你好："+userName;
    }

    @GetMapping("test")
    @ResponseBody
    @Monitor(text = "接口描述", value = "接口描述", title = "接口标题")
    public String test(String userName){
        return "你好："+userName;
    }
}
