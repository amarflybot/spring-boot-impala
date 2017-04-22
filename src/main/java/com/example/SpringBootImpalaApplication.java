package com.example;

import com.cloudera.impala.jdbc.common.AbstractDataSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
public class SpringBootImpalaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootImpalaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(final AbstractDataSource abstractDataSource) {
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				Connection connection = abstractDataSource.getConnection();
				Statement statement = connection.createStatement();
				statement.setFetchSize(5);
				//statement.executeUpdate("INSERT INTO TABLE WEB_STAT VALUES (cast('NA' as char(2)), 'testDomain', current_timestamp(), 'log', 1, 2, 3)");
				ResultSet resultSet = statement.executeQuery("SELECT * FROM WEB_STAT");
				long currentTimeMillis = System.currentTimeMillis();
				while (resultSet.next()){
					System.out.println(resultSet.getInt(1));
					System.out.println(resultSet.getString(2));
				}
				long currentTimeMillis1 = System.currentTimeMillis();
				System.out.println("Total time taken: "+(currentTimeMillis1-currentTimeMillis));
				System.out.println("Done");
				statement.close();
				connection.close();
			}
		};
	}
}
