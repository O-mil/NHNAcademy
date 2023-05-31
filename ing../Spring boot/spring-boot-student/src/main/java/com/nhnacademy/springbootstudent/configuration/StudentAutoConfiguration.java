package com.nhnacademy.springbootstudent.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableConfigurationProperties(StudentProperties.class)
public class StudentAutoConfiguration {

//    @Bean
//    public String a() {
//        return new String(c());
//    }
//
//    @Bean
//    public String b() {
//        return new String();
//    }
//
//    @Bean
//    public String c() {
//        System.out.println("=======================> c");
//        return new String("c");
//    }
//    //C 한 번 실행함.
//    // @Configuration으로 잡혀있기 때문에 c를 실행하면 실제적으로 c를 실행하지 않고
//    // proxy가 받아서 던져주는 역할만 함.
}
