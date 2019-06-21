package ru.eremin.tm.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TmFrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmFrontendApplication.class, args);
    }

}
