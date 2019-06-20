package ru.eremin.tm.backend.rest;

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
import ru.eremin.tm.backend.api.IntegrationTest;
import ru.eremin.tm.backend.model.dto.ProjectDTO;
import ru.eremin.tm.backend.model.dto.TaskDTO;
import ru.eremin.tm.backend.model.dto.web.LoginRequest;
import ru.eremin.tm.backend.model.dto.web.ResponseAuthEntity;
import ru.eremin.tm.backend.model.entity.enumerated.Status;
import ru.eremin.tm.backend.rest.feign.AuthClient;
import ru.eremin.tm.backend.rest.feign.ProjectClient;
import ru.eremin.tm.backend.rest.feign.TaskClient;

import java.util.List;

import static junit.framework.TestCase.*;

/**
 * @autor av.eremin on 04.06.2019.
 */

@Category(IntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskRestTest {

    private static final String USER_ID = "8208421e-86df-11e9-bc42-526af7764f64";

    @LocalServerPort
    private int port;

    private String token = "";

    private String projectId = "";

    @Before
    public void before() {
        token = auth();
        projectId = getProject();
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName("testTask");
        taskDTO.setProjectId(projectId);
        taskDTO.setUserId(USER_ID);
        @NotNull final TaskClient taskClient = TaskClient.client(port);
        taskClient.createTask(token, taskDTO);
    }

    @After
    public void after() {
        @Nullable final String token = auth();
        @NotNull final TaskClient taskClient = TaskClient.client(port);
        @NotNull final ProjectClient projectClient = ProjectClient.client(port);
        projectClient.deleteAllProjects(token, USER_ID);
        taskClient.deleteAllTasks(token, USER_ID);
    }

    @Test
    public void findAllTest() {
        @NotNull final TaskClient taskClient = TaskClient.client(port);
        @NotNull final List<TaskDTO> taskDTOS = taskClient.findAllTasks(token, USER_ID);
        assertTrue(!taskDTOS.isEmpty());
    }

    @Test
    public void findOneTest() {
        @NotNull final TaskClient taskClient = TaskClient.client(port);
        @NotNull final List<TaskDTO> taskDTOS = taskClient.findAllTasks(token, USER_ID);
        assertTrue(!taskDTOS.isEmpty());
        @Nullable final TaskDTO taskDTO = taskClient.findOneTask(token, taskDTOS.get(0).getId());
        assertNotNull(taskDTO);
    }

    @Test
    public void updateTest() {
        @NotNull final TaskClient taskClient = TaskClient.client(port);
        @NotNull final List<TaskDTO> taskDTOS = taskClient.findAllTasks(token, USER_ID);
        assertTrue(!taskDTOS.isEmpty());
        @NotNull final TaskDTO taskDTO = taskDTOS.get(0);

        assertFalse(taskDTO.getStatus().equals(Status.DONE));
        taskDTO.setStatus(Status.DONE);
        taskClient.updateTask(token, taskDTO);

        @Nullable final TaskDTO taskDTOtmp = taskClient.findOneTask(token, taskDTO.getId());
        assertNotNull(taskDTOtmp);
        assertEquals(taskDTOtmp.getStatus(), Status.DONE);
    }

    @Test(expected = FeignException.class)
    public void deleteTest() {
        @NotNull final TaskClient taskClient = TaskClient.client(port);
        @NotNull final List<TaskDTO> taskDTOS = taskClient.findAllTasks(token, USER_ID);
        assertTrue(!taskDTOS.isEmpty());
        @NotNull final TaskDTO taskDTO = taskDTOS.get(0);

        taskClient.deleteTask(token, taskDTO.getId());
        @Nullable final TaskDTO taskDTOtmp = taskClient.findOneTask(token, taskDTO.getId());
    }

    @Test
    public void findByProjectTest() {
        @NotNull final TaskClient taskClient = TaskClient.client(port);
        @Nullable final List<TaskDTO> taskDTOS = taskClient.findTaskByProjectId(token, projectId);
        assertTrue(!taskDTOS.isEmpty());
    }

    private String getProject() {
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName("testProject");
        projectDTO.setUserId(USER_ID);
        @NotNull final ProjectClient projectClient = ProjectClient.client(port);
        projectClient.createProject(token, projectDTO);
        return projectClient.findAllProjects(token, USER_ID).get(0).getId();
    }

    private String auth() {
        @NotNull final AuthClient authClient = AuthClient.client(port);
        @Nullable final ResponseAuthEntity responseEntity = authClient.auth(new LoginRequest("testUser", "pass"));
        return "Bearer " + responseEntity.getToken();
    }

}
