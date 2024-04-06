package com.likelion.lionshop_sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LionshopSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(LionshopSampleApplication.class, args);
    }

}
