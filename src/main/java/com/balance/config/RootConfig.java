package com.balance.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by hangelov on 21/11/2016.
 */

@Configuration
@ComponentScan(basePackages={"com.balance"},
        excludeFilters={
                @ComponentScan.Filter(type= FilterType.ANNOTATION, value=EnableWebMvc.class)
        })
@ImportResource({"classpath*:/balance-context.xml"})
@EnableWebMvc
public class RootConfig extends WebMvcConfigurerAdapter {

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/css/**").addResourceLocations("/static/css/");
                registry.addResourceHandler("/img/**").addResourceLocations("/static/img/");
                registry.addResourceHandler("/js/**").addResourceLocations("/static/js/");
        }

}
