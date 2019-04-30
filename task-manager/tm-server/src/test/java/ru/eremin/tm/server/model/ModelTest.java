package ru.eremin.tm.server.model;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.eremin.tm.server.api.IProjectService;
import ru.eremin.tm.server.api.ISessionService;
import ru.eremin.tm.server.api.ITaskService;
import ru.eremin.tm.server.api.IUserService;
import ru.eremin.tm.server.config.DBConfig;
import ru.eremin.tm.server.config.EntityFactory;
import ru.eremin.tm.server.config.Order;
import ru.eremin.tm.server.config.OrderedRunner;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ProjectDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.dto.TaskDTO;
import ru.eremin.tm.server.model.dto.UserDTO;
import ru.eremin.tm.server.service.ProjectService;
import ru.eremin.tm.server.service.SessionService;
import ru.eremin.tm.server.service.TaskService;
import ru.eremin.tm.server.service.UserService;

import javax.persistence.EntityManagerFactory;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

@RunWith(OrderedRunner.class)
public class ModelTest {

    private static ProjectDTO projectDTO;
    private static TaskDTO taskDTO;
    private static UserDTO userDTO;
    private static SessionDTO sessionDTO;

    private static IProjectService projectService;
    private static ITaskService taskService;
    private static IUserService userService;
    private static ISessionService sessionService;

    @BeforeClass
    public static void before() {
        userDTO = EntityFactory.getUser();
        projectDTO = EntityFactory.getProject(userDTO);
        taskDTO = EntityFactory.getTask(projectDTO, userDTO);
        sessionDTO = EntityFactory.getSession(userDTO);

        final EntityManagerFactory entityManagerFactory = DBConfig.getFactory();
        userService = new UserService(entityManagerFactory);
        taskService = new TaskService(entityManagerFactory);
        projectService = new ProjectService(entityManagerFactory);
        sessionService = new SessionService(entityManagerFactory);
    }

    @Test
    @Order(order = 1)
    public void insertTest() throws AccessForbiddenException, IncorrectDataException {
        final int beforeUsersSize = userService.findAll().size();
        final int beforeProjectsSize = projectService.findByUserId(userDTO.getId()).size();
        final int beforeTasksSize = taskService.findByUserId(userDTO.getId()).size();
        final int beforeSessionSize = sessionService.findAll().size();

        userService.persist(userDTO);
        projectService.persist(projectDTO);
        taskService.persist(taskDTO);
        sessionService.persist(sessionDTO);

        assertEquals(beforeUsersSize + 1, userService.findAll().size());
        assertEquals(beforeProjectsSize + 1, projectService.findByUserId(userDTO.getId()).size());
        assertEquals(beforeTasksSize + 1, taskService.findByUserId(userDTO.getId()).size());
        assertEquals(beforeSessionSize + 1, sessionService.findAll().size());
    }

    @Test
    @Order(order = 2)
    public void findTest() throws IncorrectDataException, AccessForbiddenException {
        assertEquals(userDTO.getId(), userService.findOne(userDTO.getId()).getId());
        assertEquals(userDTO.getId(), userService.findByLogin(userDTO.getLogin()).getId());
        assertEquals(projectDTO.getId(), projectService.findOne(projectDTO.getId()).getId());
        assertNotNull(projectService.findByUserId(userDTO.getId()));
        assertEquals(taskDTO.getId(), taskService.findOne(taskDTO.getId()).getId());
        assertNotNull(taskService.findByProjectId(projectDTO.getId()));
        assertNotNull(taskService.findByUserId(userDTO.getId()));
        assertNotNull(sessionService.findOne(sessionDTO.getId()));
    }

    @Test
    @Order(order = 3)
    public void updateTest() throws IncorrectDataException {
        final UserDTO userTMP = userService.findOne(userDTO.getId());
        final ProjectDTO projectTMP = projectService.findOne(projectDTO.getId());
        final TaskDTO taskTMP = taskService.findOne(taskDTO.getId());

        assertNotNull(userTMP);
        assertNotNull(projectTMP);
        assertNotNull(taskTMP);

        final String updateName = "UpdateName";

        userTMP.setLogin(updateName);
        taskTMP.setName(updateName);
        projectTMP.setName(updateName);
        userService.update(userTMP);
        projectService.update(projectTMP);
        taskService.update(taskTMP);

        assertEquals(updateName, userService.findOne(userDTO.getId()).getLogin());
        assertEquals(updateName, projectService.findOne(projectDTO.getId()).getName());
        assertEquals(updateName, taskService.findOne(taskDTO.getId()).getName());
    }
    @Test
    @Order(order = 4)
    public void mergeTest() throws IncorrectDataException {
        final UserDTO userDTO1 = EntityFactory.getUser();

        final ProjectDTO projectDTO1 = EntityFactory.getProject(userDTO1);

        final TaskDTO taskDTO1 = EntityFactory.getTask(projectDTO1, userDTO1);

        userService.merge(userDTO1);
        projectService.merge(projectDTO1);
        taskService.merge(taskDTO1);

        assertNotNull(userService.findOne(userDTO1.getId()));
        assertNotNull(projectService.findOne(projectDTO1.getId()));
        assertNotNull(taskService.findOne(taskDTO1.getId()));

        userDTO1.setLogin("UpdateLogin");
        projectDTO1.setName("UpdateName");
        taskDTO1.setName("UpdateName");

        userService.merge(userDTO1);
        projectService.merge(projectDTO1);
        taskService.merge(taskDTO1);

        assertEquals("UpdateLogin", userService.findOne(userDTO1.getId()).getLogin());
        assertEquals("UpdateName", projectService.findOne(projectDTO1.getId()).getName());
        assertEquals("UpdateName", taskService.findOne(taskDTO1.getId()).getName());

        taskService.remove(taskDTO1.getId());
        projectService.remove(projectDTO1.getId());
        userService.remove(userDTO1.getId());
    }


    @Test
    @Order(order = 5)
    public void deleteTest() throws IncorrectDataException, AccessForbiddenException {
        final int beforeUsersSize = userService.findAll().size();
        final int beforeProjectsSize = projectService.findByUserId(userDTO.getId()).size();
        final int beforeTasksSize = taskService.findByUserId(userDTO.getId()).size();
        final int beforeSessionsSize = sessionService.findAll().size();

        taskService.remove(taskDTO.getId());
        projectService.remove(projectDTO.getId());
        sessionService.remove(sessionDTO.getId());
        userService.remove(userDTO.getId());

        assertEquals(beforeUsersSize - 1, userService.findAll().size());
        assertEquals(beforeProjectsSize - 1, projectService.findByUserId(userDTO.getId()).size());
        assertEquals(beforeTasksSize - 1, taskService.findByUserId(userDTO.getId()).size());
        assertEquals(beforeTasksSize - 1, taskService.findByUserId(userDTO.getId()).size());
        assertEquals(beforeSessionsSize - 1, sessionService.findAll().size());
    }

}
