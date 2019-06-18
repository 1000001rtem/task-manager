package ru.eremin.tm.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.eremin.tm.api.service.IProjectService;
import ru.eremin.tm.api.service.ITaskService;
import ru.eremin.tm.api.service.IUserService;
import ru.eremin.tm.config.EntityFactory;
import ru.eremin.tm.exeption.AccessForbiddenException;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.Project;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


/**
 * @autor Eremin Artem on 18.06.2019.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PaginationTest {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IUserService userService;

    private static String userId;

    @Before
    public void init() throws IncorrectDataException {
        UserDTO userDTO = EntityFactory.getUser();
        userService.persist(userDTO);
        userId = userDTO.getId();
        final List<ProjectDTO> projectDTOS = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ProjectDTO projectDTO = EntityFactory.getProject(userDTO);
            projectDTO.setName("Project " + i);
            projectDTOS.add(projectDTO);
        }
        projectDTOS.forEach(e-> {
            try {
                projectService.persist(e);
            } catch (IncorrectDataException e1) {
                e1.printStackTrace();
            }
        });

        for (int i = 0; i < 100; i++) {
            TaskDTO taskDTO = EntityFactory.getTask(projectDTOS.get(0), userDTO);
            taskDTO.setName("Task " + i);
            taskService.persist(taskDTO);
        }
    }

    @Test
    public void projectTest() throws AccessForbiddenException {
        final int page = 5;
        final int size = 3;
        final String nameFirstElement = "Project " + page*size;
        Pageable pageable = PageRequest.of(page, size);
        Page<ProjectDTO> result = projectService.findByUserId(userId, pageable);
        assertEquals(result.getContent().get(0).getName(), nameFirstElement);
        assertEquals(result.getSize(), size);
        assertEquals(result.getNumberOfElements(), size);
        assertEquals(result.getNumber(), page);
    }

    @Test
    public void taskService(){
        final int page = 5;
        final int size = 3;
        final String nameFirstElement = "Task " + page*size;
        Pageable pageable = PageRequest.of(page, size);
        Page<TaskDTO>result = taskService.findByUserId(userId, pageable);
        assertEquals(result.getContent().get(0).getName(), nameFirstElement);
        assertEquals(result.getSize(), size);
        assertEquals(result.getNumberOfElements(), size);
        assertEquals(result.getNumber(), page);
    }

}
