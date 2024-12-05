package com.metacoding.upload;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        // 1. 절대경로 file:///c:/upload/
        // 2. 상대경로 file:./upload/
        registry
                .addResourceHandler("/upload/**") // html에서 경로를 적으면

//                 file://localhost/c:/workspace/spring_lec/upload/images/ -> 주소를 다 적으면
//                 여긴 경로 시작이 upload폴더 부터
                .addResourceLocations("file:" + "./images/") // 웹서버의 /images/ 폴더 경로를 찾음
                .setCachePeriod(60 * 60); // 초 단위 => 한시간


    }
}