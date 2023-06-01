package com.nhnacademy.springbootstudent.configuration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="nhn.student")
@Getter
@Setter
public class StudentProperties {
    private String name;
}
