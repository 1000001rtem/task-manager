package ru.eremin.tm.rest;

import feign.FeignException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.eremin.tm.api.IntegrationTest;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.web.LoginRequest;
import ru.eremin.tm.model.dto.web.ResponseAuthEntity;
import ru.eremin.tm.model.entity.enumerated.Status;
import ru.eremin.tm.rest.feign.AuthClient;
import ru.eremin.tm.rest.feign.ProjectClient;

import java.util.List;

import static junit.framework.TestCase.*;

/**
 * @autor av.eremin on 04.06.2019.
 */

@Category(IntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectRestTest {

    private static final String USER_ID = "8208421e-86df-11e9-bc42-526af7764f64";

    @LocalServerPort
    private int port;

    private String token = "";

    @Before
    public void before() {
        token = auth();
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName("testProject");
        projectDTO.setUserId(USER_ID);
        @NotNull final ProjectClient projectClient = ProjectClient.client(port);
        projectClient.createProject(token, projectDTO);
    }

    @After
    public void after() {
        @NotNull final ProjectClient projectClient = ProjectClient.client(port);
        projectClient.deleteAllProjects(token, USER_ID);
    }

    @Test
    public void findAllTest() {
        @NotNull final ProjectClient projectClient = ProjectClient.client(port);
        @NotNull final List<ProjectDTO> projectDTOS = projectClient.findAllProjects(token, USER_ID);
        assertTrue(!projectDTOS.isEmpty());
    }

    @Test
    public void findOneTest() {
        @NotNull final ProjectClient projectClient = ProjectClient.client(port);
        @NotNull final List<ProjectDTO> projectDTOS = projectClient.findAllProjects(token, USER_ID);
        assertTrue(!projectDTOS.isEmpty());
        @Nullable final ProjectDTO project = projectClient.findOneProject(token, projectDTOS.get(0).getId());
        assertNotNull(project);
    }

    @Test
    public void updateTest() {
        @NotNull final ProjectClient projectClient = ProjectClient.client(port);
        @NotNull final List<ProjectDTO> projectDTOS = projectClient.findAllProjects(token, USER_ID);
        assertTrue(!projectDTOS.isEmpty());
        @NotNull final ProjectDTO projectDTO = projectDTOS.get(0);

        assertFalse(projectDTO.getStatus().equals(Status.DONE));
        projectDTO.setStatus(Status.DONE);
        projectClient.updateProject(token, projectDTO);

        @Nullable final ProjectDTO projectDTOtmp = projectClient.findOneProject(token, projectDTO.getId());
        assertEquals(projectDTO.getStatus(), Status.DONE);
    }

    @Test(expected = FeignException.class)
    public void deleteTest() {
        @NotNull final ProjectClient projectClient = ProjectClient.client(port);
        @NotNull final List<ProjectDTO> projectDTOS = projectClient.findAllProjects(token, USER_ID);
        assertTrue(!projectDTOS.isEmpty());
        @NotNull final ProjectDTO projectDTO = projectDTOS.get(0);
        projectClient.deleteProject(token, projectDTO.getId());

        @Nullable final ProjectDTO projectDTOtmp = projectClient.findOneProject(token, projectDTO.getId());
    }

    private String auth() {
        @NotNull final AuthClient authClient = AuthClient.client(port);
        @Nullable final ResponseAuthEntity responseEntity = authClient.auth(new LoginRequest("testUser", "pass"));
        return "Bearer " + responseEntity.getToken();
    }

}
