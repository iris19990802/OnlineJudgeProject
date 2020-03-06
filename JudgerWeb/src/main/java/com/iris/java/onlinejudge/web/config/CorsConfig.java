package com.iris.java.onlinejudge.web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

// 配置类：允许跨域请求
@Configuration
public class CorsConfig {

    public CorsConfig() {
    }

    @Bean
    public CorsFilter corsFilter(){

        /*
            1: 添加cors配置信息
          */
        CorsConfiguration config = new CorsConfiguration();

        // 允许指定地址跨站请求 （前端运行在5000端口，允许前端请求）
        config.addAllowedOrigin("http://localhost:5000");

        // 允许请求携带一些附加信息（比如cookie）
        config.setAllowCredentials(true);

        // 允许的请求方式（GET/POST/全部）
        config.addAllowedMethod("*");

        // 设置允许的Header参数
        config.addAllowedHeader("*");



        /*
            2: 为url添加请求的路径
         */

        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**",config);


        /*
            3：返回重新定义好的 corsSource
         */
        return new CorsFilter(corsSource);
    }
}
