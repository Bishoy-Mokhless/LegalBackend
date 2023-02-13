/*
package com.kazyonplus.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                        .allowedHeaders("*")
                        .exposedHeaders("*")
                        .allowedOriginPatterns("*")
                        .allowCredentials(true);


            }
        };
		*/
/* private void addCORSHeaders(HttpServletResponse response) {
        response.addHeader("Access-control-Allow-Origin", "http://localhost:4200/");
        response.addHeader("Access-control-Allow-Methods", "POST , GET , OPTIONS , PUT , DELETE, PATCH");
        response.addHeader("Access-control-Allow-Headers", "Authorization , Origin , content-type");
        response.addHeader("Access-control-Allow-Credentials", "true");
        response.addHeader("Access-control-Max-Age", "1728000");
        response.addHeader("Access-Control-Expose-Headers", "accessToken, refreshToken");
    }*//*

    }
}*/
