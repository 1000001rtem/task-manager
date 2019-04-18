package ru.eremin.tm.server.model.repository.api;

import ru.eremin.tm.server.model.entity.Project;

import java.util.List;

/**
 * @autor Eremin Artem on 08.04.2019.
 */

public interface IProjectRepository extends IRepository<Project> {

    List<Project> findByUserId(String userId);

    void removeAll(String userId);

}
