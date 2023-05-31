package com.nhnacademy.board.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.board.Base;
import com.nhnacademy.board.domain.Post;
import com.nhnacademy.board.domain.User;
import com.nhnacademy.board.domain.Visitor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;


@Configuration
@ComponentScan(basePackageClasses = Base.class,
    excludeFilters = { @ComponentScan.Filter(Controller.class)})
public class RootConfig {


//    @Bean
//    public UserRepository userRepository() {
//        UserRepository userRepository = new UserRepository();
//        userRepository.add(new User("admin","1234","관리자", null));
//        for (int i = 1; i < 100; i++) {
//            String id = "student" + i;
//            String password = i+"q";
//            String name = "아카데미" + i;
//            userRepository.add(new User(id,password,name,null));
//        }
//        return userRepository;
//    }
//    @Bean
//    public PostRepository postRepository(){
//        PostRepository postRepository = new PostRepository();
//        postRepository.register(new Post(Long.valueOf(1),"가입인사","반가워요","marco"));
//
//        return postRepository;
//    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:~/spring-jpa;DATABASE_TO_UPPER=false;"
                + "INIT=RUNSCRIPT FROM 'classpath:/script/schema.sql'");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        dataSource.setInitialSize(10);
        dataSource.setMaxTotal(10);
        dataSource.setMinIdle(10);
        dataSource.setMaxIdle(10);

        dataSource.setMaxWaitMillis(1000);

        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);

        return dataSource;
    }

    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    @Bean
    public ObjectMapper ObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        //pretty print json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        //value -> null 무시
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //LocalDate, LocalDateTime support jsr310
        objectMapper.registerModule(new JavaTimeModule());
        //timestamp 출력을 disable. -> 문자열형태로 출력
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }
}
