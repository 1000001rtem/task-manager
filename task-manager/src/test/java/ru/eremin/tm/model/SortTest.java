package ru.eremin.tm.model;

import org.junit.BeforeClass;
import org.junit.Test;
import ru.eremin.tm.config.EntityFactory;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.enumerated.Status;
import ru.eremin.tm.model.repository.ProjectRepository;
import ru.eremin.tm.model.repository.TaskRepository;
import ru.eremin.tm.model.repository.UserRepository;
import ru.eremin.tm.model.repository.api.IProjectRepository;
import ru.eremin.tm.model.repository.api.ITaskRepository;
import ru.eremin.tm.model.repository.api.IUserRepository;
import ru.eremin.tm.model.service.ProjectService;
import ru.eremin.tm.model.service.TaskService;
import ru.eremin.tm.model.service.UserService;
import ru.eremin.tm.model.service.api.IProjectService;
import ru.eremin.tm.model.service.api.ITaskService;
import ru.eremin.tm.model.service.api.IUserService;

import java.util.Date;

/**
 * @autor av.eremin on 15.04.2019.
 */

public class SortTest {


    private static IProjectService projectService;
    private static ITaskService taskService;
    private static IUserService userService;

    @BeforeClass
    public static void before() {
        final IProjectRepository projectRepository = new ProjectRepository();
        final ITaskRepository taskRepository = new TaskRepository();
        final IUserRepository userRepository = new UserRepository();
        userService = new UserService(userRepository);
        taskService = new TaskService(taskRepository);
        projectService = new ProjectService(projectRepository, taskService);
    }

    @Test
    public void createDateTest() throws InterruptedException {
        final UserDTO userDTO = EntityFactory.getUser();
        for (int i = 0; i < 100; i++) {
            final ProjectDTO projectDTO = EntityFactory.getProject(userDTO);
            projectDTO.setName("project" + i);
            projectDTO.setEndDate(new Date(System.currentTimeMillis() + (long) (i * 1000000000)));
            if (i % 3 == 0) projectDTO.setStatus(Status.DURING);
            else if (i % 5 == 0) projectDTO.setStatus(Status.PLANNED);
            else if (i % 2 == 0) projectDTO.setStatus(Status.DONE);
            projectService.persist(projectDTO);
        }

//        final List<ProjectDTO> projects = projectService.findAllSortedByStatus();
//        for (final ProjectDTO project : projects) {
//            System.out.println(project.info());
//        }
    }
}
