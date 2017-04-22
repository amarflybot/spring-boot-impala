package com.example;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by amarendra on 21/4/17.
 */
@Configuration
public class AppConfig {

    @Bean
    JdbcTemplate jdbcTemplate(final DataSource dataSource){
        return new JdbcStream(dataSource);
    }
}
