package ru.eremin.tm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import ru.eremin.tm.model.entity.Project;
import ru.eremin.tm.model.entity.Task;
import ru.eremin.tm.model.entity.User;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * @autor av.eremin on 07.05.2019.
 */

@Repository(TaskRepository.NAME)
public interface TaskRepository extends JpaRepository<Task, String> {

    String NAME = "taskRepository";

    @Query("SELECT e FROM Task e WHERE e.project = ?1")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Task> findByProject(Project project);

    @Query("SELECT e FROM Task e WHERE e.user = ?1")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Task> findByUser(User user);

    @Query("SELECT e FROM Task e WHERE e.user = ?1")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Page<Task> findByUser(User user, Pageable pageable);

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
