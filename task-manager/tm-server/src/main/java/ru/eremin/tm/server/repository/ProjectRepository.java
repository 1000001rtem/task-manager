package ru.eremin.tm.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.eremin.tm.server.model.entity.Project;
import ru.eremin.tm.server.model.entity.User;

import java.util.List;

/**
 * @autor av.eremin on 07.05.2019.
 */

@Repository(ProjectRepository.NAME)
public interface ProjectRepository extends JpaRepository<Project, String> {

    String NAME = "projectRepository";

    @Query("SELECT e FROM Project e WHERE e.user = ?1")
    List<Project> findByUser(User user);

    @Query("SELECT e FROM Project e WHERE e.user = ?1 ORDER BY e.createDate")
    List<Project> findAllSortedByCreateDate(User user);

    @Query("SELECT e FROM Project e WHERE e.user = ?1 ORDER BY e.startDate")
    List<Project> findAllSortedByStartDate(User user);

    @Query("SELECT e FROM Project e WHERE e.user = ?1 ORDER BY e.endDate")
    List<Project> findAllSortedByEndDate(User user);

    @Query("SELECT e FROM Project e WHERE e.user = ?1 ORDER BY e.status")
    List<Project> findAllSortedByStatus(User user);

    @Query("SELECT e FROM Project e WHERE e.user = ?1 and e.name LIKE (concat('%', ?2,'%'))")
    List<Project> findByName(User user, String name);

    @Query("SELECT e FROM Project e WHERE e.user = ?1 and e.description LIKE (concat('%', ?2,'%'))")
    List<Project> findByDescription(User user, String description);

}
