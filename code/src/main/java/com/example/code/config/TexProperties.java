package com.example.code.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "txt")
@PropertySource("classpath:/txt.properties")
@Setter
@Getter
public class TexProperties {
    private String success;
    private String  exception;
    private String param_is_null;
    private String element_is_exists;
    private String no_hava_iinventory;
}
