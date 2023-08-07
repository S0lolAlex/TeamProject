package com.example.DevTeamProject_Notes.datasourse.postgres;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Profile("prod")
@Configuration
public class DataSourceConfigPostgres {
    @Value("${DB_HOST}")
    private String dbHost;
    @Value("${DB_PORT}")
    private String dbPort;
    @Value("${DB_NAME}")
    private String dbName;
    @Value("${DB_USERNAME}")
    private String dbUsername;
    @Value("${DB_PASSWORD}")
    private String dbPassword;

    @Bean

    public DataSource dataSourceProd() {
        String jdbcUrl = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;
        DriverManagerDataSource dataSourceProd = new DriverManagerDataSource();
        dataSourceProd.setDriverClassName("org.postgresql.Driver");
        dataSourceProd.setUrl(jdbcUrl);
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



