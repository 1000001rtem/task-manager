package ru.eremin.tm.model.service.api;

import ru.eremin.tm.model.dto.ProjectDTO;
import ru.eremin.tm.model.entity.Project;

import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public interface IProjectService extends IService<Project, ProjectDTO> {

    List<ProjectDTO> findByUserId(String userId);

    void removeAll(String userId);

}
