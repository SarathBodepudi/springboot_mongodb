package com.mongodb.starter;

import com.mongodb.starter.controllers.PersonController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationStarter {
    private final static Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);
    }
}
