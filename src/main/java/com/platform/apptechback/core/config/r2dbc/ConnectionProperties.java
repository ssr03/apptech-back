package com.platform.apptechback.core.config.r2dbc;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "r2dbc.datasource")
public class ConnectionProperties {
    private String host;
    private Integer port;
    private String db;
    private String username;
    private String password;
}
