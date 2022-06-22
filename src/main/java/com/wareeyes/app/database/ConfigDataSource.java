package com.wareeyes.app.database;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class ConfigDataSource {

    @Bean
    public DataSource source() {
        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");

        ds.setUrl("jdbc:mysql://remotemysql.com:3306/jgKkyMewz1");
        ds.setUsername("jgKkyMewz1");
        ds.setPassword("Gur2O37z7C");

        return ds;
    }
}