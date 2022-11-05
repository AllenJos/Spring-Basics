package com.example.demomysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DBConfig {

    public Logger logger = LoggerFactory.getLogger(DBConfig.class);

    @Bean
    public Connection getConnection(){
        logger.info("Inside");
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbl36_person", "root", "admin");
        }
        catch (SQLException ex){
            throw new RuntimeException();
        }
    }
}
