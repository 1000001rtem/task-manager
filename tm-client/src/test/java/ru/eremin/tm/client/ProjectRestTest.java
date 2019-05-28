package ru.eremin.tm.client;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.eremin.tm.client.model.dto.ProjectDTO;
import ru.eremin.tm.client.model.dto.ResultDTO;
import ru.eremin.tm.client.model.dto.enumerated.Status;

import static junit.framework.TestCase.*;

/**
 * @autor av.eremin on 28.05.2019.
 */

public class ProjectRestTest {

    private static final String URL = "http://localhost:8080/api/project";
    private static final String USER_ID = "22e0196c-3b09-4b5b-909f-a541eb584706"; // test user id

    @Before
    public void before() {
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName("testProject");
        projectDTO.setDescription("testDesc");
        projectDTO.setUserId(USER_ID);
        @NotNull final RestTemplate restTemplate = new RestTemplate();
        @NotNull final HttpEntity<ProjectDTO> request = new HttpEntity<>(projectDTO);
        @Nullable final ResultDTO resultDTO = restTemplate.postForObject(URL + "/create", request, ResultDTO.class);
        assertTrue(resultDTO.isSuccess());
    }

    @After
    public void after() {
        final int beforeSize = findAll().length;
        @NotNull final UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(URL + "/delete")
                .queryParam("projectId", findAll()[0].getId());
        @Nullable final RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(builder.toUriString());
        assertEquals(beforeSize - 1, findAll().length);
    }

    @Test
    public void findOneTest() {
        @NotNull final UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(URL + "/findOne")
                .queryParam("projectId", findAll()[0].getId());
        @Nullable final RestTemplate restTemplate = new RestTemplate();
        @Nullable final ProjectDTO projectDTO = restTemplate.getForObject(builder.toUriString(), ProjectDTO.class);
        assertNotNull(projectDTO);
    }

    @Test
    public void updateTest() {
        @NotNull final UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(URL + "/findOne")
                .queryParam("projectId", findAll()[0].getId());
        @Nullable final RestTemplate restTemplate = new RestTemplate();
        @Nullable final ProjectDTO projectDTO = restTemplate.getForObject(builder.toUriString(), ProjectDTO.class);
        assertFalse(projectDTO.getStatus().equals(Status.DONE));
        projectDTO.setStatus(Status.DONE);
        @NotNull final HttpEntity<ProjectDTO> request = new HttpEntity<>(projectDTO);
        restTemplate.put(URL + "/update", request);
        @Nullable final ProjectDTO updateProjectDTO = restTemplate.getForObject(builder.toUriString(), ProjectDTO.class);
        assertEquals(updateProjectDTO.getStatus(), Status.DONE);
    }

    @Test
    public void findAllTest() {
        assertNotNull(findAll());
        assertTrue(findAll().length > 0);
    }

    @Nullable
    private ProjectDTO[] findAll() {
        @NotNull final UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(URL + "/findAll")
                .queryParam("userId", USER_ID);
        @Nullable final RestTemplate restTemplate = new RestTemplate();
        //if List.class int returned List<LinkedHashMap>
        @Nullable final ProjectDTO[] projectDTOS = restTemplate.getForObject(builder.toUriString(), ProjectDTO[].class);
        return projectDTOS;
    }

}
