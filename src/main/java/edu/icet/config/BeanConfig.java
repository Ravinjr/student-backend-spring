package edu.icet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class BeanConfig {
    @Bean
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
