package com.iris.java.onlinejudge.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

//    http://localhost:8080/swagger-ui.html     (原生界面）
//    http://localhost:8080/doc.html     （Bootstrap界面）

    // 配置swagger2核心配置 docket
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)  // 指定api类型为swagger2
                .apiInfo(apiInfo())                 // 用于定义api文档汇总信息
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.iris.java.onlinejudge.web.controller"))   // 指定controller包
                .paths(PathSelectors.any())         // 所有controller
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("online judge api doc")        // 文档页标题
                .contact(new Contact("iris",
                        "",
                        "Liuyuexuan1999@126.com"))        // 联系人信息
                .description("")  // 详细信息
                .version("1.0.1")   // 文档版本号
                //.termsOfServiceUrl("https://www.lza1111.com") // 网站地址
                .build();
    }

}
