/**
 * Copyright 2020 靳磊. All rights reserved
 * Project Name:mose
 * Module Name:TODO:Module
 */
package mose;

import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * what:    (这里用一句话描述这个类的作用). <br/>
 * when:    (这里描述这个类的适用时机 – 可选).<br/>
 * how:     (这里描述这个类的使用方法 – 可选).<br/>
 * warning: (这里描述这个类的注意事项 – 可选).<br/>
 *
 * @author mose created on 2020/4/28
 */
@Configuration()
public class CommonConfiguration {
    @Bean
    public PropertyResourceConfigurer propertyResourceConfigurer() {
        PropertyResourceConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        // 使用的PropertySourcesPlaceholderConfigurer，不用自己再手动指定亦可处理占位符~~~
        // configurer.setLocation(new ClassPathResource("my.properties")); // 加载指定的属性文件
        return configurer;
    }
}
