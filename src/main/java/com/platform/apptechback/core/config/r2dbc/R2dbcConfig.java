package com.platform.apptechback.core.config.r2dbc;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.reactive.TransactionalOperator;

import java.time.Duration;

import static io.r2dbc.pool.PoolingConnectionFactoryProvider.*;
import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@RequiredArgsConstructor
@Configuration
@EnableR2dbcRepositories(
        basePackages = {"com.platform.apptechback.domain.*"}
)
@EnableR2dbcAuditing
@EnableTransactionManagement
public class R2dbcConfig extends AbstractR2dbcConfiguration {
    private final ConnectionProperties connectionProperties;

    private final R2dbcPoolProperties r2dbcPoolProperties;

    @Primary
    @Bean
    @Override
    public ConnectionFactory connectionFactory() {
         return ConnectionFactories.get(
                 ConnectionFactoryOptions.builder()
                    .option(DRIVER, POOLING_DRIVER)
                    .option(PROTOCOL, "postgresql")
                    .option(HOST, connectionProperties.getHost())
                    .option(USER, connectionProperties.getUsername())
                    .option(PORT, connectionProperties.getPort())
                    .option(PASSWORD, connectionProperties.getPassword())
                    .option(DATABASE, connectionProperties.getDb())
                    .option(MAX_SIZE, r2dbcPoolProperties.getMaxSize())
                    .option(INITIAL_SIZE, r2dbcPoolProperties.getInitialSize())
                    .option(MAX_IDLE_TIME, Duration.ofSeconds(r2dbcPoolProperties.getMaxIdleTime()))
                    .option(MAX_CREATE_CONNECTION_TIME, Duration.ofSeconds(r2dbcPoolProperties.getMaxCreateConnectionTime()))
                    .option(MAX_LIFE_TIME, Duration.ofSeconds(r2dbcPoolProperties.getMaxLife()))
                    .option(MAX_ACQUIRE_TIME, Duration.ofSeconds(r2dbcPoolProperties.getMaxAcquireTime()))
                    .option(Option.valueOf("tlsVersion"), r2dbcPoolProperties.getTlsVersion())
                    .build()
                );
    }

    @Primary
    @Bean
    public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Primary
    @Bean
    public TransactionalOperator transactionalOperator(ReactiveTransactionManager reactiveTransactionManager) {
        return TransactionalOperator.create(reactiveTransactionManager);
    }
}
