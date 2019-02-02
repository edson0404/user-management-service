package com.blackswan.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.invoke.MethodHandles;

@SpringBootApplication
public class Application {

    static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        log.info("Application is starting.....");
        SpringApplication.run(Application.class, args);
    }
}

