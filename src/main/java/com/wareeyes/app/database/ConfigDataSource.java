package com.wareeyes.app.database;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigDataSource {

    @Bean
    public static DataSource source() {

        DataSourceBuilder<?> dsb = DataSourceBuilder.create();
        dsb.driverClassName("com.mysql.cj.jdbc.Driver");

        dsb.url("jdbc:mysql://localhost:3306/wareeyes-db");
        dsb.username("root");
        dsb.password("root");

        return dsb.build();
    }
}