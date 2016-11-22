package com.balance.config;

import org.springframework.web.servlet.support.
        AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

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

/*
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }


    private MultipartConfigElement getMultipartConfigElement() {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(	LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        return multipartConfigElement;
    }


    private static final String LOCATION = "D:" + File.separator +"songs" + File.separator; // Temporary location where files will be stored

    private static final long MAX_FILE_SIZE = 104857600; // 100MB : Max file size.
    // Beyond that size spring will throw exception.
    private static final long MAX_REQUEST_SIZE = 20971520; // 20MB : Total request size containing Multi part.

    private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk
    */
}