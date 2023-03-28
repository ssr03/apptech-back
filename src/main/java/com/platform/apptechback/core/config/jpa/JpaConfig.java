package com.platform.apptechback.core.config.jpa;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableJpaAuditing
@RequiredArgsConstructor
@Configuration
@EnableJpaRepositories(
        basePackages = {"com.platform.apptechback.domain.*"}
)
@EnableTransactionManagement
public class JpaConfig{
    private final ConnectionProperties connectionProperties;

    private final JpaPoolProperties jpaPoolProperties;

    @Bean
    public DataSource dataSource(){
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setUsername(connectionProperties.getUsername());
        hikariConfig.setPassword(connectionProperties.getPassword());
        hikariConfig.setJdbcUrl(connectionProperties.getUrl());
        hikariConfig.setDataSourceClassName(connectionProperties.getDriverClassName());

        hikariConfig.setConnectionTimeout(jpaPoolProperties.getConnectionTimeout());
        hikariConfig.setMaximumPoolSize(jpaPoolProperties.getMaximumPoolSize());
        hikariConfig.setMaxLifetime(jpaPoolProperties.getMaxLifetime());
        hikariConfig.setIdleTimeout(jpaPoolProperties.getIdleTimeout());
        hikariConfig.setMinimumIdle(jpaPoolProperties.getMinimumIdle());

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("com.platform.apptechback.domain.*.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
         return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
