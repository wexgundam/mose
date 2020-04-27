/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:core
 */
package mose.core.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * what:  service层异常拦截记录，用于捕捉Service层的所有异常信息，并记录日志
 * 
 *
 * @author mose created on 2017年6月13日
 */
@Aspect
public class ServiceExceptionAspect {
	/**
	 * 定义日志输出位置
	 */
    private static Logger logger = LoggerFactory.getLogger("serviceLog");
/**
 * 
 * what:   service异常拦截处理
 * 
 * @param joinPoint 连接点
 * @param e 异常对象
 *
 * @author mose created on 2017年6月13日
 */
    @AfterThrowing(value = "execution (* mose.*.service.*.*(..))", throwing = "e")
    public void loggingException(JoinPoint joinPoint, Exception e) {
        // 拦截的实体类
        Object target = joinPoint.getTarget(); 
        // 拦截的方法名称
        String methodName = joinPoint.getSignature().getName();
        logger.error("实体类:" + target);
        logger.error("方法名:" + methodName);
        logger.error("异常类名：" + joinPoint.getSignature().getName().getClass());
        // 得到被拦截方法参数，并打印
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            logger.error("抛异常拦截： 被拦截到的方法参数：" + i + " -- " + args[i]);
        }
        logger.error("异常信息: " + e.getMessage());
    }
}
