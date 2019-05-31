package ru.eremin.tm.client;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.eremin.tm.client.model.dto.*;
import ru.eremin.tm.client.model.dto.enumerated.Status;
import ru.eremin.tm.client.task.AuthClient;
import ru.eremin.tm.client.task.ProjectClient;
import ru.eremin.tm.client.task.TaskClient;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.*;

/**
 * @autor av.eremin on 28.05.2019.
 */

public class TaskRestTest {

    private static final String URL = "http://localhost:8080/api/task";
    private static final String USER_ID = "6706e691-2f78-45ad-b021-3730c48959f0"; // test user id

    @Before
    public void before() {
        @Nullable final String token = auth();
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName("testTask");
        taskDTO.setProjectId(getProject());
        taskDTO.setUserId(USER_ID);
        @NotNull final TaskClient taskClient = TaskClient.client(URL);
        taskClient.createTask(token, taskDTO);
    }

    @After
    public void after() {
        @Nullable final String token = auth();
        @NotNull final TaskClient taskClient = TaskClient.client(URL);
        taskClient.deleteTask(token, findAll().get(0).getId());
        deleteProject();
    }

    @Test
    public void findAllTest() {
        assertTrue(findAll().size() > 0);
    }

    @Test
    public void findOneTest() {
        @Nullable final String token = auth();
        @NotNull final TaskClient taskClient = TaskClient.client(URL);
        @Nullable final TaskDTO taskDTO = taskClient.findOneTask(token, findAll().get(0).getId());
        System.out.println(taskDTO);
        assertNotNull(taskDTO);
    }

    @Test
    public void updateTest() {
        @Nullable final String token = auth();
        @NotNull final TaskClient taskClient = TaskClient.client(URL);
        @Nullable final TaskDTO taskDTO = taskClient.findOneTask(token, findAll().get(0).getId());
        assertFalse(taskDTO.getStatus().equals(Status.DONE));
        taskDTO.setStatus(Status.DONE);
        taskClient.updateTask(token, taskDTO);
        @Nullable final TaskDTO updatedTaskDTO = taskClient.findOneTask(token, findAll().get(0).getId());
        assertEquals(updatedTaskDTO.getStatus(), Status.DONE);
    }

    private List<TaskDTO> findAll() {
        @Nullable final String token = auth();
        @NotNull final TaskClient taskClient = TaskClient.client(URL);
        return taskClient.findAllTasks(token, USER_ID);
    }

    private String getProject() {
        @Nullable final String token = auth();
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName("testProject");
        projectDTO.setDescription("testDesc");
        projectDTO.setUserId(USER_ID);
        @NotNull final ProjectClient projectClient = ProjectClient.client("http://localhost:8080/api/project");
        projectClient.createProject(token, projectDTO);
        return projectClient.findAllProjects(token, USER_ID).get(0).getId();
    }

    private void deleteProject() {
        @Nullable final String token = auth();
        @NotNull final ProjectClient projectClient = ProjectClient.client("http://localhost:8080/api/project");
        projectClient.deleteProject(token, projectClient.findAllProjects(token, USER_ID).get(0).getId());
    }

    private String auth(){
        @NotNull final AuthClient authClient = AuthClient.client("http://localhost:8080/api");
        @Nullable final ResponseSoapEntity responseEntity = authClient.auth(new LoginRequest("admin", "pass"));
        return "Bearer " + responseEntity.getToken();
    }

}
