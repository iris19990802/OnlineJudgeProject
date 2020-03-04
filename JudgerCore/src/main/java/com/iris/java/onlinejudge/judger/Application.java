package com.iris.java.onlinejudge.judger;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.iris.java.onlinejudge.judger.mapper.normal")
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }

}
