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
import org.springframework.web.bind.annotation.*;
import ru.eremin.tm.model.dto.ResultDTO;
import ru.eremin.tm.model.dto.TaskDTO;

import java.util.List;

/**
 * @autor av.eremin on 28.05.2019.
 */

@FeignClient("taskClient")
public interface TaskClient {

    static TaskClient client(final int port) {
        @Nullable final String baseUrl = "http://localhost:" + port + "/api/task";
        final FormHttpMessageConverter converter = new FormHttpMessageConverter();
        final HttpMessageConverters converters = new HttpMessageConverters(converter);
        final ObjectFactory<HttpMessageConverters> objectFactory = () -> converters;
        return Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(new SpringEncoder(objectFactory))
                .decoder(new SpringDecoder(objectFactory))
                .target(TaskClient.class, baseUrl);
    }

    @GetMapping(value = "/findAll")
    List<TaskDTO> findAllTasks(@RequestHeader("Authorization") String token,
                               @RequestParam(name = "userId") @Nullable final String userId);

    @GetMapping(value = "/findOne", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    TaskDTO findOneTask(@RequestHeader("Authorization") String token,
                        @RequestParam(name = "taskId") @Nullable final String taskId);

    @GetMapping(value = "/findByProject", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<TaskDTO> findTaskByProjectId(@RequestHeader("Authorization") String token,
                                      @RequestParam(name = "projectId") @Nullable final String projectId);

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultDTO createTask(@RequestHeader("Authorization") String token,
                         @RequestBody @Nullable final TaskDTO taskDTO);

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultDTO updateTask(@RequestHeader("Authorization") String token,
                         @RequestBody @Nullable final TaskDTO taskDTO);

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultDTO deleteTask(@RequestHeader("Authorization") String token,
                         @RequestParam(name = "taskId") @Nullable final String taskId);

    @DeleteMapping(value = "/deleteAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResultDTO deleteAllTasks(@RequestHeader("Authorization") String token,
                             @RequestParam(name = "userId") @Nullable final String userId);

}
