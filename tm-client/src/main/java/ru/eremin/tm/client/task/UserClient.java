package ru.eremin.tm.client.task;

import feign.Feign;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.support.SpringDecoder;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import ru.eremin.tm.client.model.dto.ChangePasswordDTO;
import ru.eremin.tm.client.model.dto.ResultDTO;
import ru.eremin.tm.client.model.dto.UserDTO;

import java.util.List;

/**
 * @autor av.eremin on 29.05.2019.
 */
@FeignClient("userClient")
public interface UserClient {

    static UserClient client(final String baseUrl) {
        final FormHttpMessageConverter converter = new FormHttpMessageConverter();
        final HttpMessageConverters converters = new HttpMessageConverters(converter);
        final ObjectFactory<HttpMessageConverters> objectFactory = () -> converters;
        return Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(new SpringEncoder(objectFactory))
                .decoder(new SpringDecoder(objectFactory))
                .target(UserClient.class, baseUrl);
    }

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<UserDTO> findAllUsers(@RequestHeader("Authorization") String token);

    @GetMapping(value = "/findOne", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    UserDTO findOneUser(@RequestHeader("Authorization") String token,
                        @RequestParam(name = "userId") @Nullable final String userId);

    @GetMapping(value = "/findByLogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    UserDTO findUserByLogin(@RequestHeader("Authorization") String token,
                            @RequestParam(name = "userLogin") @Nullable final String userLogin);

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultDTO createUser(@RequestHeader("Authorization") String token,
                         @RequestBody @Nullable final UserDTO userDTO);

    @PutMapping(value = "/changePassword", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultDTO changePassword(@RequestHeader("Authorization") String token,
                             @RequestBody @Nullable ChangePasswordDTO changePasswordDTO);

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultDTO updateUser(@RequestHeader("Authorization") String token,
                         @RequestBody @Nullable final UserDTO userDTO);

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultDTO deleteUser(@RequestHeader("Authorization") String token,
                         @RequestParam(name = "userId") @Nullable final String userId);

}
