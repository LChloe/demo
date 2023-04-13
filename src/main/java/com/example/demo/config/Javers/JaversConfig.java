package com.example.demo.config.Javers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JaversConfig {

    @Bean
    public JaversAuthorProvider javersAuthorProvider() {
        return new JaversAuthorProvider();
    }
}
