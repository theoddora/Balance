package com.balance.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by hangelov on 21/11/2016.
 */

@Configuration
@ComponentScan(basePackages={"com.balance"},
        excludeFilters={
                @ComponentScan.Filter(type= FilterType.ANNOTATION, value=EnableWebMvc.class)
        })
public class RootConfig extends WebMvcConfigurerAdapter{




}
