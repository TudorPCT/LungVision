package com.lungvision.lungdiseasexrayclassificationbe.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        URI databaseUri = new URI(System.getenv("DATABASE_URL"));

        String username = databaseUri.getUserInfo().split(":")[0];
        String password = databaseUri.getUserInfo().split(":")[1];
        String databaseUrl = "jdbc:postgresql://" +
                databaseUri.getHost() + ':' +
                databaseUri.getPort() +
                databaseUri.getPath();

        return DataSourceBuilder.create()
                .url(databaseUrl)
                .username(username)
                .password(password)
                .build();
    }

}
