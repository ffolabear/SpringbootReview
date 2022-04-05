package com.springreview;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackages = "com.springreview.domain.member",
        basePackageClasses = AutoAppConfig.class)
public class AutoAppConfig {


}
