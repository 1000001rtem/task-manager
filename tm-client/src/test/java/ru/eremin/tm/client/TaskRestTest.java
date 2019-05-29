package ru.eremin.tm.client;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.eremin.tm.client.model.dto.ProjectDTO;
import ru.eremin.tm.client.model.dto.ResultDTO;
import ru.eremin.tm.client.model.dto.TaskDTO;
import ru.eremin.tm.client.model.dto.enumerated.Status;
import ru.eremin.tm.client.task.ProjectClient;
import ru.eremin.tm.client.task.TaskClient;

import java.util.List;

import static junit.framework.TestCase.*;

/**
 * @autor av.eremin on 28.05.2019.
 */

public class TaskRestTest {

    private static final String URL = "http://localhost:8080/api/task";
    private static final String USER_ID = "22e0196c-3b09-4b5b-909f-a541eb584706"; // test user id

    @Before
    public void before() {
        @NotNull final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName("testTask");
        taskDTO.setProjectId(getProject());
        taskDTO.setUserId(USER_ID);
        @NotNull final TaskClient taskClient = TaskClient.client(URL);
        taskClient.createTask(taskDTO);
    }

    @After
    public void after() {
        @NotNull final TaskClient taskClient = TaskClient.client(URL);
        taskClient.deleteTask(findAll().get(0).getId());
        deleteProject();
    }

    @Test
    public void findAllTest() {
        assertTrue(findAll().size() > 0);
    }

    @Test
    public void findOneTest() {
        @NotNull final TaskClient taskClient = TaskClient.client(URL);
        @Nullable final TaskDTO taskDTO = taskClient.findOneTask(findAll().get(0).getId());
        assertNotNull(taskDTO);
    }

    @Test
    public void updateTest() {
        @NotNull final TaskClient taskClient = TaskClient.client(URL);
        @Nullable final TaskDTO taskDTO = taskClient.findOneTask(findAll().get(0).getId());
        assertFalse(taskDTO.getStatus().equals(Status.DONE));
        taskDTO.setStatus(Status.DONE);
        taskClient.updateTask(taskDTO);
        @Nullable final TaskDTO updatedTaskDTO = taskClient.findOneTask(findAll().get(0).getId());
        assertEquals(updatedTaskDTO.getStatus(), Status.DONE);
    }

    private List<TaskDTO> findAll() {
        @NotNull final TaskClient taskClient = TaskClient.client(URL);
        return taskClient.findAllTasks(USER_ID);
    }

    private String getProject() {
        @NotNull final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName("testProject");
        projectDTO.setDescription("testDesc");
        projectDTO.setUserId(USER_ID);
        @NotNull final ProjectClient projectClient = ProjectClient.client("http://localhost:8080/api/project");
        projectClient.createProject(projectDTO);
        return projectClient.findAllProjects(USER_ID).get(0).getId();
    }

    private void deleteProject() {
        @NotNull final ProjectClient projectClient = ProjectClient.client("http://localhost:8080/api/project");
        projectClient.deleteProject(projectClient.findAllProjects(USER_ID).get(0).getId());
    }

}
