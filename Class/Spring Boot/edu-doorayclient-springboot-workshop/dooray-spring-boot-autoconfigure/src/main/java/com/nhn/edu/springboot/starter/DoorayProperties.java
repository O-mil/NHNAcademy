package com.nhn.edu.springboot.starter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * TODO(1) application.property 에서 dooray.hook-url 을 문자열로 받을 수 있도록 속성을 선언하세요.
 */


@Getter
@Setter
@ConfigurationProperties("dooray")
public class DoorayProperties {

    private String hookUrl;

}
