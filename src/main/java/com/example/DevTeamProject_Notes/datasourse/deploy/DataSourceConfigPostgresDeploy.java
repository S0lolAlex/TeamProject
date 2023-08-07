package com.example.DevTeamProject_Notes.datasourse.deploy;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Profile("deploy")
@Configuration
public class DataSourceConfigPostgresDeploy {
    @Value("${spring.datasource.url}")
    private String dbHost;
    @Value("${spring.datasource.username}")
    private String dbUsername;
    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean

    public DataSource dataSourceProd() {
        DriverManagerDataSource dataSourceProd = new DriverManagerDataSource();
        dataSourceProd.setUrl(dbHost);
        dataSourceProd.setUsername(dbUsername);
        dataSourceProd.setPassword(dbPassword);
        return dataSourceProd;
    }

    @Bean(initMethod = "migrate")
    public Flyway flywayProd() {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSourceProd())
                .locations("classpath:static/migration/postgres")
                .load();
        flyway.migrate();
        return flyway;
    }
}



