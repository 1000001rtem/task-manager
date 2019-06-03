package ru.eremin.tm;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * @autor av.eremin on 03.06.2019.
 */

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public ServletRegistrationBean cxfServletRegistration() {
//        // http://cxf.apache.org/docs/using-cxf-and-cdi-11-jsr-346.html
//        ServletRegistrationBean registration = new ServletRegistrationBean(new CXFServlet(), "/service/*");
//        registration.setLoadOnStartup(1);
//        return registration;
//    }
}
