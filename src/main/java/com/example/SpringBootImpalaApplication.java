package com.example;

import com.cloudera.dsi.dataengine.utilities.TimestampTz;
import com.cloudera.jdbc.common.AbstractDataSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

@SpringBootApplication
public class SpringBootImpalaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootImpalaApplication.class, args);
	}

	//@Bean
	public CommandLineRunner commandLineRunner(final DataSource abstractDataSource) {
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				Connection connection = abstractDataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO WEB_STAT VALUES (?,?,?,?,?,?,?)");
				statement.setFetchSize(5);
				statement.setString(1,"NA");
				statement.setString(2,"domTest");
				statement.setTimestamp(3, TimestampTz.valueOf(LocalDateTime.now()));
				statement.setString(4,"testFeature");
				statement.setInt(5, 2);
				statement.setInt(6, 6);
				statement.setInt(7, 7);
				statement.execute();
				statement.close();
				connection.close();
				connection = abstractDataSource.getConnection();
				Statement statement1 = connection.createStatement();
				ResultSet resultSet = statement1.executeQuery("SELECT * FROM WEB_STAT");
				long currentTimeMillis = System.currentTimeMillis();
				while (resultSet.next()){
					System.out.println(resultSet.getInt(1));
					System.out.println(resultSet.getString(2));
				}
				long currentTimeMillis1 = System.currentTimeMillis();
				System.out.println("Total time taken: "+(currentTimeMillis1-currentTimeMillis));
				System.out.println("Done");
				connection.commit();
				statement1.close();
				connection.close();
			}
		};
	}
}
