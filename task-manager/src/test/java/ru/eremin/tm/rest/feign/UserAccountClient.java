package ru.eremin.tm.rest.feign;

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
import org.springframework.web.bind.annotation.RequestHeader;
import ru.eremin.tm.model.dto.web.ChangePasswordDTO;
import ru.eremin.tm.model.dto.web.ResultDTO;

/**
 * @autor av.eremin on 05.06.2019.
 */

@FeignClient("userAccountClient")
public interface UserAccountClient {

    static UserAccountClient client(final int port) {
        @Nullable final String baseUrl = "http://localhost:" + port + "/api/account";
        final FormHttpMessageConverter converter = new FormHttpMessageConverter();
        final HttpMessageConverters converters = new HttpMessageConverters(converter);
        final ObjectFactory<HttpMessageConverters> objectFactory = () -> converters;
        return Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(new SpringEncoder(objectFactory))
                .decoder(new SpringDecoder(objectFactory))
                .target(UserAccountClient.class, baseUrl);
    }

    @PostMapping(value = "/changePassword", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultDTO changePassword(@RequestHeader("Authorization") String token,
                             @RequestBody @Nullable final ChangePasswordDTO changePasswordDTO);

}
