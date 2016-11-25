package com.balance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


/**
 * Created by hangelov on 21/11/2016.
 */


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
