package ru.eremin.tm.backend.rest.feign;

import feign.Feign;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.eremin.tm.backend.model.dto.web.RegistrationRequest;
import ru.eremin.tm.backend.model.dto.web.ResultDTO;

/**
 * @autor av.eremin on 05.06.2019.
 */

@FeignClient("registrationClient")
public interface RegistrationClient {

    static RegistrationClient client(final int port) {
        @Nullable final String baseUrl = "http://localhost:" + port + "/api";
        final FormHttpMessageConverter converter = new FormHttpMessageConverter();
        final HttpMessageConverters converters = new HttpMessageConverters(converter);
        final ObjectFactory<HttpMessageConverters> objectFactory = () -> converters;
        return Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(new SpringEncoder(objectFactory))
                .decoder(new SpringDecoder(objectFactory))
                .target(RegistrationClient.class, baseUrl);
    }

    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultDTO registration(@RequestBody @Nullable final RegistrationRequest registrationRequest);

}
