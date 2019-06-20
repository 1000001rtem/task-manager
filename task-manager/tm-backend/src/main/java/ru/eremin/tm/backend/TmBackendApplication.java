package ru.eremin.tm.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TmBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmBackendApplication.class, args);
    }

}
