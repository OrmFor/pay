package com.kinlie.microservicepay.common;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "system.env")
@Data
public class SystemEnvironmentConfig {

    private boolean isProd;

}
