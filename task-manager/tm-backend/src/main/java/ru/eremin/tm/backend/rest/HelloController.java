package ru.eremin.tm.backend.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @autor artem on 24.06.19.
 */

@RestController
@RequestMapping(value = "/api")
public class HelloController {

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String ping() throws UnknownHostException {
        return port;
    }

}
