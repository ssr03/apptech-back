package com.platform.apptechback.core.config.r2dbc;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "r2dbc.pool")
public class R2dbcPoolProperties {
    private Integer initialSize;
    private Integer maxSize;
    private Long maxLife;
    private Long maxCreateConnectionTime;
    private Long maxIdleTime;
    private Long maxAcquireTime;
    private String tlsVersion;
}
