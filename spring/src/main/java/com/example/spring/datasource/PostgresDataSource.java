package com.example.spring.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PostgresDataSource {
    @Bean
    @ConfigurationProperties("app.datasource")
    public HikariDataSource hikariDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
