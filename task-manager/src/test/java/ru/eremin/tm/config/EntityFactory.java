package ru.eremin.tm.config;

/**
 * @autor Eremin Artem on 17.05.2019.
 */

import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.dto.TaskDTO;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.enumerated.Role;
import ru.eremin.tm.util.PasswordHashUtil;

import java.util.Date;
import java.util.UUID;

public class EntityFactory {

    public static ProjectDTO getProject(final UserDTO userDTO) {
        final ProjectDTO project = new ProjectDTO();
        project.setId(UUID.randomUUID().toString());
        project.setName("testProject" + getRandom());
        project.setDescription("twtdtghf" + getRandom());
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        project.setUserId(userDTO.getId());
        return project;
    }

    public static TaskDTO getTask(final ProjectDTO projectDTO, final UserDTO userDTO) {
        final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName("testTask" + getRandom());
        taskDTO.setDescription("testTaskDescription" + getRandom());
        taskDTO.setStartDate(new Date());
        taskDTO.setEndDate(new Date());
        taskDTO.setProjectId(projectDTO.getId());
        taskDTO.setUserId(userDTO.getId());
        return taskDTO;
    }

    public static UserDTO getUser() {
        final UserDTO userDTO = new UserDTO();
        userDTO.setLogin("testLoginDTO" + getRandom());
        userDTO.setHashPassword(PasswordHashUtil.md5("testPasswordDTO"));
        userDTO.setRole(Role.USER);
        return userDTO;
    }

    private static int getRandom() {
        return ((int) (Math.random() * 30));
    }

}
