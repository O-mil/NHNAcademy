package com.nhnacademy.springmvc1.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = "com.nhnacademy.springmvc1", excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {
}
