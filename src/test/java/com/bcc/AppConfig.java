package com.bcc;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AppConfig implements ApplicationContextInitializer<ConfigurableApplicationContext> {
	
	  private static Boolean isDbInitialized = Boolean.FALSE;

	    @Override
	    public void initialize(ConfigurableApplicationContext ac) {
	        if (!isDbInitialized) {
	            ConfigurableEnvironment environment = ac.getEnvironment();
	            String dbUrl = environment.getProperty("spring.datasource.url");
	            String dbUser = environment.getProperty("spring.datasource.username");
	            String dbPassword = environment.getProperty("spring.datasource.password");
	            DataSource dataSource = getDataSource(dbUser, dbPassword, dbUrl);
	            //new OracleDbTestUtil(getJdbcTemplate(dataSource), dataSource);
	            isDbInitialized = Boolean.TRUE;
	        }
	    }

	    // === configure the datasource based on the application properties ===
	    private DataSource getDataSource(String user, String password, String url) {
	        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	        dataSourceBuilder.url(url);
	        dataSourceBuilder.username(user);
	        dataSourceBuilder.password(password);
	        return dataSourceBuilder.build();
	    }

	    // === configure JdbcTemplate based on the Datasource ===
	    private JdbcTemplate getJdbcTemplate(DataSource ds) {
	        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
	        return jdbcTemplate;
	    }
	    
	   
}

