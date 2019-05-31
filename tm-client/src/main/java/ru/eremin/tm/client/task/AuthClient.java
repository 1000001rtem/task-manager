package ru.eremin.tm.client.task;

import feign.Feign;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.support.SpringDecoder;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.eremin.tm.client.model.dto.LoginRequest;
import ru.eremin.tm.client.model.dto.ResponseSoapEntity;

/**
 * @autor av.eremin on 31.05.2019.
 */

@FeignClient("projectClient")
public interface AuthClient {

    static AuthClient client(final String baseUrl) {
        final FormHttpMessageConverter converter = new FormHttpMessageConverter();
        final HttpMessageConverters converters = new HttpMessageConverters(converter);
        final ObjectFactory<HttpMessageConverters> objectFactory = () -> converters;
        return Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(new SpringEncoder(objectFactory))
                .decoder(new SpringDecoder(objectFactory))
                .target(AuthClient.class, baseUrl);
    }

    @PostMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseSoapEntity auth(@RequestBody @Nullable final LoginRequest loginRequest);
}
