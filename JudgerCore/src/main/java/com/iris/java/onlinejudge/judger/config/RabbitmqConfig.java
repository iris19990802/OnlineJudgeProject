package com.iris.java.onlinejudge.judger.config;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitmqConfig {

    public static final String SUBMIT_QUERY_QUEUE = "submit_query";
    public static final String JUDGE_RESULT_QUEUE = "judge_result";
    public static final String HEARTBEAT_QUEUE = "heartbeat";

    @Bean
    public Queue queue1(){
        return new Queue(SUBMIT_QUERY_QUEUE,true);
    }

    @Bean
    public Queue queue2(){
        return new Queue(JUDGE_RESULT_QUEUE,true);
    }

    @Bean
    public Queue queue3(){
        return new Queue(HEARTBEAT_QUEUE,true);
    }

}
