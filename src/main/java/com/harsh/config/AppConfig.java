package com.harsh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration file to setup component scan
 * Created by Harsh on 22-Jul-17.
 */

@Configuration
@ComponentScan(basePackages = {"com.harsh"})
public class AppConfig {
}
