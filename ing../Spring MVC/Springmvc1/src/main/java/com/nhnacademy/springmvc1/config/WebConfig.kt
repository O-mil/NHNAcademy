package com.nhnacademy.springmvc1.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import java.lang.Override

@Configuration
@EnableWebMvc
//@ComponentScan(basePackages = {"com.nhnacademy.springmvc1.**.controller"})
@ComponentScan(basePackageClasses = {com.nhnacademy.springmvc1.controller.ControllerBase.class})
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void

}