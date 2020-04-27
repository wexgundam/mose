/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:core
 */
package mose.core.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * what:  以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候中取出ApplicaitonContext.
 *
 * @author mose created on 2017年5月23日
 */
public class SpringContextHolder implements ApplicationContextAware {

	/**
	 * Application容器
	 */
    private static ApplicationContext applicationContext;

    /**
     * 
     * why: 接口实现类
     * warning: 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     *
     * @param applicationContext Application容器
     *
     * @author mose created on 2017年5月23日
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext; //NOSONAR
    }

   /**
    * 
    * what: 取得存储在静态变量中的ApplicationContext.
    * 
    * @return ApplicationContext
    *
    * @author mose created on 2017年5月23日
    */
    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    /**
     * 
     * what: 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * 
     * @param name Bean的name
     * @param <T> 泛型类
     * @return bean
     *
     * @author mose created on 2017年5月23日
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 
     * what: 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * 如果有多个Bean符合Class, 取出第一个.
     * 
     * @param requiredType Class
     * @param <T> 泛型类
     * @return bean
     *
     * @author mose created on 2017年5月23日
     */
    public static <T> T getBean(Class<T> requiredType) {
        checkApplicationContext();
        return applicationContext.getBean(requiredType);
    }

    /**
     * 
     * what: 清除applicationContext静态变量.
     *
     * @author mose created on 2017年5月23日
     */
    public static void cleanApplicationContext() {
        applicationContext = null;
    }
    /**
     * 
     * what:  检查applicaitonContext是否注入
     *
     * @author mose created on 2017年5月23日
     */
    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
        }
    }
}
