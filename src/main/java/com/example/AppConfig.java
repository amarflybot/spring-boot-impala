package com.example;



import org.apache.hadoop.security.UserGroupInformation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by amarendra on 21/4/17.
 */
@Configuration
public class AppConfig {

    @Value("${bigdata.impala.connectionURL}")
    private String connectionURL;

    @Value("${bigdata.impala.username}")
    private String userName;

    @Value("${bigdata.impala.password}")
    private String password;

    @Value("${bigdata.kerberos.keyTabLocation}")
    private String keyTabLocation;

    @Value("${bigdata.kerberos.krb5Location}")
    private String krb5Location;

    @Value("${bigdata.kerberos.jaasLocation}")
    private String jaasLocation;

    @Value("${mobigdataurya.kerberos.priniciple}")
    private String priniciple;

    @Bean
    public DataSource dataSource() throws IOException {
        System.setProperty("javax.security.auth.useSubjectCredsOnly", "true");
        System.setProperty("java.security.krb5.conf", "/Users/amarendra/IdeaProjects/spring-boot-impala/src/main/resources/krb5.conf");
        System.setProperty("java.security.auth.login.config","/Users/amarendra/IdeaProjects/spring-boot-impala/src/main/resources/jaas.conf");
        //System.setProperty("java.security.krb5.conf", krb5Location);
        //System.setProperty("java.security.auth.login.config", jaasLocation);
        org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
        conf.set("hadoop.security.authentication", "kerberos");
        UserGroupInformation.setConfiguration(conf);
        UserGroupInformation.loginUserFromKeytab("impala/quickstart.cloudera@CLOUDERA", "/Users/amarendra/IdeaProjects/spring-boot-impala/src/main/resources/impala.keytab");
        //UserGroupInformation.loginUserFromKeytab(priniciple, keyTabLocation);



        com.cloudera.impala.jdbc41.DataSource dataSource =
                new com.cloudera.impala.jdbc41.DataSource();
        dataSource.setURL(connectionURL);

        return dataSource;
    }

    @Bean
    JdbcTemplate jdbcTemplate(final DataSource dataSource){
        return new JdbcStream(dataSource);
    }
}
