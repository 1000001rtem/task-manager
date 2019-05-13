package ru.eremin.tm.server.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.eremin.tm.server.api.IProjectService;
import ru.eremin.tm.server.api.ISessionService;
import ru.eremin.tm.server.api.ITaskService;
import ru.eremin.tm.server.api.IUserService;
import ru.eremin.tm.server.config.EntityFactory;
import ru.eremin.tm.server.config.TestConfiguration;
import ru.eremin.tm.server.exeption.AccessForbiddenException;
import ru.eremin.tm.server.exeption.IncorrectDataException;
import ru.eremin.tm.server.model.dto.ProjectDTO;
import ru.eremin.tm.server.model.dto.SessionDTO;
import ru.eremin.tm.server.model.dto.TaskDTO;
import ru.eremin.tm.server.model.dto.UserDTO;
import ru.eremin.tm.server.model.entity.enumerated.Role;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * @autor av.eremin on 07.05.2019.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class ServiceTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private ISessionService sessionService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ITaskService taskService;

    @Test
    public void test() throws IncorrectDataException, AccessForbiddenException {
        final UserDTO user = EntityFactory.getUser();
        final SessionDTO session = EntityFactory.getSession(user);
        final ProjectDTO project = EntityFactory.getProject(user);
        final TaskDTO task = EntityFactory.getTask(project, user);

        final int beforeSessionSize = sessionService.findAll().size();
        final int beforeUsersSize = userService.findAll().size();
        final int beforeProjectsSize = projectService.findByUserId(user.getId()).size();
        final int beforeTasksSize = taskService.findByUserId(user.getId()).size();


        userService.persist(user);
        sessionService.persist(session);
        projectService.persist(project);
        taskService.persist(task);

        assertNotNull(userService.findOne(user.getId()));
        assertNotNull(sessionService.findOne(session.getId()));
        assertNotNull(projectService.findOne(project.getId()));
        assertNotNull(taskService.findOne(task.getId()));

        final String update = "update";

        user.setLogin(update);
        session.setUserRole(Role.ADMIN);
        project.setName(update);
        task.setName(update);

        userService.update(user);
        sessionService.update(session);
        projectService.update(project);
        taskService.update(task);

        assertEquals(update, userService.findOne(user.getId()).getLogin());
        assertEquals(Role.ADMIN, sessionService.findOne(session.getId()).getUserRole());
        assertEquals(update, projectService.findOne(project.getId()).getName());
        assertEquals(update, taskService.findOne(task.getId()).getName());

        sessionService.remove(session.getId());
        taskService.remove(task.getId());
        projectService.remove(project.getId());
        userService.remove(user.getId());

        assertEquals(beforeSessionSize, sessionService.findAll().size());
        assertEquals(beforeUsersSize, userService.findAll().size());
        assertEquals(beforeProjectsSize, projectService.findByUserId(user.getId()).size());
        assertEquals(beforeTasksSize, taskService.findByUserId(user.getId()).size());
    }

}
