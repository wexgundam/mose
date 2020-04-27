/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:core
 */
package mose.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * what: 用于监控系统性能，service层性能有问题的记录下来
 *
 * @author mose created on 2017年6月13日
 */
@Aspect
public class PerformanceAspect {
    /**
     * 定义日志输出位置
     */
    private static Logger logger = LoggerFactory.getLogger("performanceLog");

    /**
     * what: 环绕通知，统计操作慢的service中方法
     *
     * @param joinPoint 连接点
     *
     * @return obj
     *
     * @throws Throwable Throwable
     * @author mose created on 2017年6月13日
     */
    @Around("execution (* mose.*.service.*.*(..))")
    public Object performanceIterceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        long l = System.currentTimeMillis();
        result = joinPoint.proceed();
        long consume = System.currentTimeMillis() - l;
        if (consume > 3000) {
            // 记录系统操作较慢的service处理过程
            logger.info("实体类:" + joinPoint.getTarget());
            logger.info("方法名:" + joinPoint.getSignature().getName());
            // 得到被拦截方法参数，并打印
            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                logger.info("方法参数：" + i + " -- " + args[i]);
            }
            logger.info("用时：" + consume);
        }
        return result;
    }
}
