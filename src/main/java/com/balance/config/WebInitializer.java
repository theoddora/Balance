package com.balance.config;

import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;
@ImportResource("multipart.properties")
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final String LOCATION = "H:" + File.separator +"Balance" + File.separator + "Balance" + File.separator +"src" + File.separator + "main" + File.separator + "webapp" + File.separator + "static" + File.separator + "img" + File.separator+ "work" + File.separator;
    private static final long MAX_FILE_SIZE = 104857600; // 100MB
    private static final long MAX_REQUEST_SIZE = 20971520; // 20MB
    private static final int FILE_SIZE_THRESHOLD = 0;

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                SpringWebConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/", "*.html", "*.pdf"};
    }


    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }


    private MultipartConfigElement getMultipartConfigElement() {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(	LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        return multipartConfigElement;
    }

}