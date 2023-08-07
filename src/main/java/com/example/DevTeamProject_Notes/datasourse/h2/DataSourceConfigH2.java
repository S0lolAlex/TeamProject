package com.example.DevTeamProject_Notes.datasourse.h2;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Profile("default")
@Configuration
public class DataSourceConfigH2 {
    @Bean
    public DataSource dataSource() {
        String jdbcUrl = "jdbc:h2:mem:testmvc55;DB_CLOSE_DELAY=-1";
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername("");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource())
                .locations("classpath:static/migration/h2")
                .load();
        flyway.migrate();
        return flyway;
    }
}
