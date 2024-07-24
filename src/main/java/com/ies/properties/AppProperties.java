package com.ies.properties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data // Lombok annotation to generate setters and getters
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "admin")
public class AppProperties {

    /**
     * Loads properties from application.properties.
     * 
     * This map holds messages configured in the application.properties file
     * under the prefix "admin". The messages are accessible via getters and setters.
     */
    private Map<String, String> message = new HashMap<>();

}




