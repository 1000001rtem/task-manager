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

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * @autor av.eremin on 06.05.2019.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppClientConfiguration.class)
public class ProjectCommandsTest {

    @Autowired
    private ProjectEndpoint projectEndpoint;

    private SessionDTO sessionDTO;

    @Before
    public void before() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        sessionDTO = auth(null);
    }

    @Test
    @Category(IntegrationTest.class)
    public void createTest() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        final ProjectDTO projectDTO = EntityFactory.getProject(sessionDTO.getUserId());
        assertEquals(0, projectEndpoint.findAllProjects(sessionDTO).size());
        projectEndpoint.persistProject(sessionDTO, projectDTO);
        assertEquals(1, projectEndpoint.findAllProjects(sessionDTO).size());
    }

    @Test
    @Category(IntegrationTest.class)
    public void findTest() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        final ProjectDTO projectDTO = EntityFactory.getProject(sessionDTO.getUserId());
        assertEquals(0, projectEndpoint.findAllProjects(sessionDTO).size());
        projectEndpoint.persistProject(sessionDTO, projectDTO);
        final List<ProjectDTO> projectDTOS = projectEndpoint.findAllProjects(sessionDTO);
        assertEquals(1, projectDTOS.size());
        assertNotNull(projectEndpoint.findOneProject(sessionDTO, projectDTOS.get(0).getId()));
    }

    @Test
    @Category(IntegrationTest.class)
    public void updateTest() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        final ProjectDTO projectDTO = EntityFactory.getProject(sessionDTO.getUserId());
        assertEquals(0, projectEndpoint.findAllProjects(sessionDTO).size());
        projectEndpoint.persistProject(sessionDTO, projectDTO);
        final List<ProjectDTO> projectDTOS = projectEndpoint.findAllProjects(sessionDTO);
        assertEquals(1, projectDTOS.size());
        final ProjectDTO projectDTO1 = projectEndpoint.findOneProject(sessionDTO, projectDTOS.get(0).getId());
        projectDTO1.setStatus(Status.DONE);
        projectEndpoint.updateProject(sessionDTO, projectDTO1);
        assertEquals(Status.DONE, projectEndpoint.findAllProjects(sessionDTO).get(0).getStatus());
    }

    @Test
    @Category(IntegrationTest.class)
    public void deleteTest() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        final ProjectDTO projectDTO = EntityFactory.getProject(sessionDTO.getUserId());
        assertEquals(0, projectEndpoint.findAllProjects(sessionDTO).size());
        projectEndpoint.persistProject(sessionDTO, projectDTO);
        final List<ProjectDTO> projectDTOS = projectEndpoint.findAllProjects(sessionDTO);
        assertEquals(1, projectDTOS.size());
        projectEndpoint.removeProject(sessionDTO, projectDTOS.get(0).getId());
        assertEquals(0, projectEndpoint.findAllProjects(sessionDTO).size());
    }

    @Test
    @Category(IntegrationTest.class)
    public void findByNameTest() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        final ProjectDTO projectDTO = EntityFactory.getProject(sessionDTO.getUserId());
        assertEquals(0, projectEndpoint.findAllProjects(sessionDTO).size());
        projectEndpoint.persistProject(sessionDTO, projectDTO);
        assertEquals(1, projectEndpoint.findProjectsByName(sessionDTO, projectDTO.getName().substring(1, 3)).size());
    }

    @Test
    @Category(IntegrationTest.class)
    public void findByDescriptionTest() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        final ProjectDTO projectDTO = EntityFactory.getProject(sessionDTO.getUserId());
        assertEquals(0, projectEndpoint.findAllProjects(sessionDTO).size());
        projectEndpoint.persistProject(sessionDTO, projectDTO);
        assertEquals(1, projectEndpoint.findProjectsByDescription(sessionDTO, projectDTO.getDescription().substring(1, 3)).size());
    }

    @After
    public void after() throws IncorrectDataException_Exception, AccessForbiddenException_Exception {
        projectEndpoint.removeAllProjects(sessionDTO);
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
