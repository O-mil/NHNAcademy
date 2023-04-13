package com.nhnacademy.springcore.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = "com.nhnacademy.springcore")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DoorayConfig {

}
