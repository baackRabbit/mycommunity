package com.glodon.mycommunity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class AlphaConfig {

    @Bean
    public SimpleDateFormat getFormathBean(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
