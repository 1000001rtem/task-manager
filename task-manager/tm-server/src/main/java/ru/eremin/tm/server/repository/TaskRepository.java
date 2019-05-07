package ru.eremin.tm.server.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import ru.eremin.tm.server.model.entity.Project;
import ru.eremin.tm.server.model.entity.Task;
import ru.eremin.tm.server.model.entity.User;

import java.util.List;

/**
 * @autor av.eremin on 07.05.2019.
 */

@Repository
public interface TaskRepository extends EntityRepository<Task, String> {

    @Query("SELECT e FROM Task e WHERE e.project = ?1")
    List<Task> findByProject(Project project);

    @Query("SELECT e FROM Task e WHERE e.user = ?1")
    List<Task> findByUser(User user);

    @Query("SELECT e FROM Task e WHERE e.user = ?1 ORDER BY e.createDate")
    List<Task> findAllSortedByCreateDate(User user);

    @Query("SELECT e FROM Task e WHERE e.user = ?1 ORDER BY e.startDate")
    List<Task> findAllSortedByStartDate(User user);

    @Query("SELECT e FROM Task e WHERE e.user = ?1 ORDER BY e.endDate")
    List<Task> findAllSortedByEndDate(User user);

    @Query("SELECT e FROM Task e WHERE e.user = ?1 ORDER BY e.status")
    List<Task> findAllSortedByStatus(User user);

    @Query("SELECT e FROM Task e WHERE e.user = ?1 and e.name LIKE (concat('%', ?2,'%'))")
    List<Task> findByName(User user, String name);

    @Query("SELECT e FROM Task e WHERE e.user = ?1 and e.description LIKE (concat('%', ?2,'%'))")
    List<Task> findByDescription(User user, String description);

}
