package com.bai.xnetblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class XnetblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(XnetblogApplication.class, args);
    }

}
