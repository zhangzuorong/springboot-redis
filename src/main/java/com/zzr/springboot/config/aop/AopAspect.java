package com.zzr.springboot.config.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 开发公司：山东海豚数据技术有限公司
 * 版权：山东海豚数据技术有限公司
 * <p>
 * AopAspect
 *
 * @author zzr
 * @created Create Time: 2019/5/13
 */
@Aspect   //定义一个切面
@Component
public class AopAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 定义切点Pointcut
    @Pointcut("execution(* com.zzr.springboot.controller.AopController.*(..))")
    public void excudeService() {
    }

    // 环绕通知
    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("----环绕通知----");
        Object o = pjp.proceed();
        return o;
    }

    // 后置通知
    @After("excudeService() && @annotation(monitor)")
    public void afterMethod(JoinPoint joinPoint,Monitor monitor) {
        logger.info("----后置通知----" + monitor.text() + ":" + monitor.title() + ":" + monitor.value());
    }

    // 前置通知
    @Before("excudeService()")
    public void beforeMethod(JoinPoint joinPoint) {
        logger.info("----前置通知----");
    }

    /**
     *
     * @param joinpoint
     * @param rvt 返回值
     */
    @AfterReturning(pointcut = "excudeService()", returning = "rvt")
    public void afterReturningMethod(JoinPoint joinpoint, Object rvt) {
        logger.info("----返回通知----");
    }

    // 异常通知
    @AfterThrowing(pointcut = "excudeService()")
    public void afterThrowingMethod(JoinPoint joinPoint) {
        logger.info("----异常通知----");
    }
}
