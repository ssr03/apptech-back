package com.platform.apptechback.core.config.jpa;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jpa.pool")
public class JpaPoolProperties {
    private Long connectionTimeout;
    private Integer maximumPoolSize;
    private Long maxLifetime;
    private Integer minimumIdle;
    private Long idleTimeout;
}
