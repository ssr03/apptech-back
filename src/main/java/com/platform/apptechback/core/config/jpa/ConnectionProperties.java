package com.platform.apptechback.core.config.jpa;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "jpa.datasource")
public class ConnectionProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
