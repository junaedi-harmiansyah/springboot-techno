package com.jun.springboot;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


//to do class configuration database heroku
@Configuration
public class DatabaseConfig {

	@Value("${spring.datasource.url}")
	private String dbUrl;
	@Value("${spring.datasource.password}")
	private String dbPassword;
	@Value("${spring.datasource.username}")
	private String dbUsername;

	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setPassword(dbPassword);
		config.setJdbcUrl(dbUrl);
		config.setUsername(dbUsername);
		return new HikariDataSource(config);
	}
}