package com.balance.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

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

        @Bean
        public MessageSource messageSource() {
                ResourceBundleMessageSource source = new ResourceBundleMessageSource();
                source.setBasename("messages");
                return source;
        }

        @Bean
        public LocaleResolver localeResolver() {
                SessionLocaleResolver resolver = new SessionLocaleResolver();
                resolver.setDefaultLocale(Locale.ENGLISH);
                return resolver;
        }

        @Override
         public void addInterceptors(InterceptorRegistry registry) {
                LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
                changeInterceptor.setParamName("language");
                registry.addInterceptor(changeInterceptor);
        }

}
