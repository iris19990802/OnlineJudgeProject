package com.iris.java.onlinejudge.web.config;

import com.iris.java.onlinejudge.web.utils.UserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ControllerArgumentConfig implements WebMvcConfigurer {
    @Autowired
    UserArgumentResolver userArgumentResolver;

    /*
    controller方法可以在参数中，自动获得 HttpRequest/Response/Models 等对象
    这些对象是由Web容器注入的

    这个注入行为是可以编辑的。
    用下面这个方法就可以编辑：
 */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        argumentResolvers.add(userArgumentResolver);

    }

}
