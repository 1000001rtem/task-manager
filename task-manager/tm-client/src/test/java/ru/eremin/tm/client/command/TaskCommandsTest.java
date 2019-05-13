package ru.eremin.tm.client.command;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.eremin.tm.client.api.IntegrationTest;
import ru.eremin.tm.client.config.AppClientConfiguration;
import ru.eremin.tm.client.util.EntityFactory;
import ru.eremin.tm.server.endpoint.*;

import java.util.List;

import static junit.framework.TestCase.*;

/**
 * @autor av.eremin on 06.05.2019.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppClientConfiguration.class)
public class TaskCommandsTest {

    @Autowired
    private TaskEndpoint taskEndpoint;

    @Autowired
    private ProjectEndpoint projectEndpoint;

    private SessionDTO sessionDTO;

    private ProjectDTO projectDTO;


    @Before
    public void before() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        sessionDTO = auth(null);
        projectDTO = EntityFactory.getProject(sessionDTO.getUserId());
        projectEndpoint.persistProject(sessionDTO, projectDTO);
        projectDTO = projectEndpoint.findAllProjects(sessionDTO).get(0);
    }

    @Test
    @Category(IntegrationTest.class)
    public void createTest() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        final TaskDTO taskDTO = EntityFactory.getTask(projectDTO);
        assertEquals(0, taskEndpoint.findAllTasks(sessionDTO).size());
        taskEndpoint.persistTask(sessionDTO, taskDTO);
        assertEquals(1, taskEndpoint.findAllTasks(sessionDTO).size());
    }

    @Test
    @Category(IntegrationTest.class)
    public void findTest() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        final TaskDTO taskDTO = EntityFactory.getTask(projectDTO);
        assertEquals(0, taskEndpoint.findAllTasks(sessionDTO).size());
        taskEndpoint.persistTask(sessionDTO, taskDTO);
        final List<TaskDTO> taskDTOS = taskEndpoint.findAllTasks(sessionDTO);
        assertEquals(1, taskDTOS.size());
        assertNotNull(taskEndpoint.findOneTask(sessionDTO, taskDTOS.get(0).getId()));
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateTest() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        final TaskDTO taskDTO = EntityFactory.getTask(projectDTO);
        assertEquals(0, taskEndpoint.findAllTasks(sessionDTO).size());
        taskEndpoint.persistTask(sessionDTO, taskDTO);
        final TaskDTO taskDTO1 = taskEndpoint.findAllTasks(sessionDTO).get(0);
        assertNotNull(taskDTO1);
        taskDTO1.setStatus(Status.DONE);
        taskEndpoint.updateTask(sessionDTO, taskDTO1);
        assertEquals(Status.DONE, taskEndpoint.findAllTasks(sessionDTO).get(0).getStatus());
    }

    @Test(expected = IncorrectDataException_Exception.class)
    @Category(IntegrationTest.class)
    public void deleteTest() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        final TaskDTO taskDTO = EntityFactory.getTask(projectDTO);
        assertEquals(0, taskEndpoint.findAllTasks(sessionDTO).size());
        taskEndpoint.persistTask(sessionDTO, taskDTO);
        final TaskDTO taskDTO1 = taskEndpoint.findAllTasks(sessionDTO).get(0);
        assertNotNull(taskDTO1);
        taskEndpoint.removeTask(sessionDTO, taskDTO1.getId());
        assertNull(taskEndpoint.findOneTask(sessionDTO, taskDTO1.getId()));
    }

    @Test
    @Category(IntegrationTest.class)
    public void findByNameTest() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        final TaskDTO taskDTO = EntityFactory.getTask(projectDTO);
        assertEquals(0, taskEndpoint.findAllTasks(sessionDTO).size());
        taskEndpoint.persistTask(sessionDTO, taskDTO);
        assertEquals(1, taskEndpoint.findTasksByName(sessionDTO, taskDTO.getName().substring(1, 3)).size());
    }

    @Test
    @Category(IntegrationTest.class)
    public void findByDescriptionTest() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        final TaskDTO taskDTO = EntityFactory.getTask(projectDTO);
        assertEquals(0, taskEndpoint.findAllTasks(sessionDTO).size());
        taskEndpoint.persistTask(sessionDTO, taskDTO);
        assertEquals(1, taskEndpoint.findTasksByDescription(sessionDTO, taskDTO.getDescription().substring(1, 3)).size());
    }

    @After
    public void after() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        projectEndpoint.removeAllProjects(sessionDTO);
        taskEndpoint.removeAllTasks(sessionDTO);
        logout(sessionDTO);
    }

    private SessionDTO auth(String password) throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        final AuthorizationEndpointService authorizationEndpointService = new AuthorizationEndpointService();
        final AuthorizationEndpoint authorizationEndpoint = authorizationEndpointService.getAuthorizationEndpointPort();
        final String login = "test";
        if (password == null || password.isEmpty()) password = "test";
        final SessionDTO session = authorizationEndpoint.login(login, password);
        return session;
    }

    private void logout(final SessionDTO sessionDTO) throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        final AuthorizationEndpointService authorizationEndpointService = new AuthorizationEndpointService();
        final AuthorizationEndpoint authorizationEndpoint = authorizationEndpointService.getAuthorizationEndpointPort();
        authorizationEndpoint.logout(sessionDTO);
    }
}
