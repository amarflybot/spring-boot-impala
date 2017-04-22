package com.example;

import com.cloudera.impala.jdbc.common.AbstractDataSource;
import com.cloudera.impala.jdbc4.ImpalaJDBC4DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by amarendra on 21/4/17.
 */
@Configuration
public class AppConfig {

    @Bean
    public AbstractDataSource dataSource() {
        AbstractDataSource dataSource = new ImpalaJDBC4DataSource();
        dataSource.setURL("jdbc:impala://quickstart.cloudera:21050/default");
        return dataSource;
    }

    @Bean
    JdbcTemplate jdbcTemplate(final AbstractDataSource abstractDataSource){
        return new JdbcStream(abstractDataSource);
    }
}
