package ru.eremin.tm.server.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class TmEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmEurekaServerApplication.class, args);
    }

}
